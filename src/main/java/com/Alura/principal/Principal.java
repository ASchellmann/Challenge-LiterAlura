package com.Alura.principal;

import com.Alura.dto.DadosAutor;
import com.Alura.dto.DadosLivro;
import com.Alura.dto.DadosResultado;
import com.Alura.model.Autor;
import com.Alura.model.Livro;
import com.Alura.repository.AutorRepository;
import com.Alura.repository.LivroRepository;
import com.Alura.service.ConsumoAPI;
import com.Alura.service.ConverteDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String URL_BASE = "https://gutendex.com/books/";
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;
    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }
    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("""
                    ========= LiterAlura =========
                    1 - Buscar livro por título
                    2 - Listar livros registrados
                    3 - Listar autores
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros por idioma
                    0 - Sair
                    """);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Encerrando aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
    private void buscarLivroPorTitulo() {
        System.out.println("Digite o título do livro:");
        var titulo = leitura.nextLine();
        var json = consumo.obterDados(URL_BASE + "?search=" + titulo.replace(" ", "+"));
        DadosResultado dados = conversor.obterDados(json, DadosResultado.class);
        Optional<DadosLivro> livroBuscado =
                dados.results().stream().findFirst();
        if (livroBuscado.isPresent()) {
            DadosLivro dadosLivro = livroBuscado.get();
            DadosAutor dadosAutor = dadosLivro.autores().get(0);
            Autor autor = new Autor(
                    dadosAutor.nome(),
                    dadosAutor.nascimento(),
                    dadosAutor.falecimento()
            );
            autorRepository.save(autor);
            Livro livro = new Livro(
                    dadosLivro.titulo(),
                    dadosLivro.idiomas().get(0),
                    dadosLivro.downloads(),
                    autor
            );
            livroRepository.save(livro);
            System.out.println("Livro salvo no banco:");
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }
    private void listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }
    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }
    private void listarAutoresVivos() {
        System.out.println("Digite o ano:");
        int ano = leitura.nextInt();
        List<Autor> autores = autorRepository.findByNascimentoLessThanEqualAndFalecimentoGreaterThanEqual(ano, ano);
        autores.forEach(System.out::println);
    }
    private void listarLivrosPorIdioma() {
        System.out.println(""" 
                Digite o idioma:
                es - Espanhol
                en - Inglês
                fr - Francês
                pt - Português
                """);
        String idioma = leitura.nextLine();
        List<Livro> livros = livroRepository.findByIdioma(idioma);
        livros.forEach(System.out::println);
    }
}