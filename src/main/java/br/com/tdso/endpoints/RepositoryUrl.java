package br.com.tdso.endpoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositoryUrl {

    private static List<String> repoUrl = new ArrayList<>(Arrays.asList("GET;http://localhost:8080/leilao",
            "POST;http://localhost:8080/leilao"));

    public static List<String> listaUrl(){
        return repoUrl.stream().map(url -> (String) url).collect(Collectors.toList());
    }
}
