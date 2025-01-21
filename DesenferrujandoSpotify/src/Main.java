import Entidades.Filme;
import Entidades.Playlist;
import Entidades.Serie;


public static void main(String[] args) {
    var filme1 = new Filme("We live in time", "Jonh Crowley", 2024, 108, "A24");
    var filme2 = new Filme("Cure", "Kiyoshi Kurosawa", 1997, 111, "Daiei Film");
    var serie1 = new Serie("Dark", "Baran Bo Odar", 2017, 1420, 3, 26, "Netflix");
    var serie2 = new Serie("SweetPea", "Kirstie Swain", 2024, 273, 2, 6, "Prime video");
    var serie3 = new Serie("Dune:Prophecy", "Kevin J. Anderson", 2024, 378 , 1, 6, "Prime video");
    var serie4 = new Serie("Daredevil", "Drew Goddard", 2015, 2340, 3, 39, "Netflix");

    var favoritos = new Playlist("Obras favoritas");

    favoritos.adicionarMidia(filme1);
    favoritos.adicionarMidia(filme2);
    favoritos.adicionarMidia(serie1);
    favoritos.adicionarMidia(serie2);
    favoritos.adicionarMidia(serie3);
    favoritos.adicionarMidia(serie4);

    System.out.println("----Obras filtradas por duração----");
    favoritos.filtrarObraPorMaiorDuracao(100).forEach(System.out::println);


    System.out.println("----Obra filtrado por letras----");
    favoritos.filtarPorNome("Cure").forEach(System.out::println);

    System.out.println("----Obra filtrado por ano de lançamento----");

    favoritos.filtraPorLancamento(2024).forEach(System.out::println);

    System.out.println("----Obra filtrada por plataforma----");

    favoritos.filtarPorStreaming("Netflix").forEach(System.out::println);



}