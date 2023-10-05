package br.com.tdso.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    private static HttpClient client = HttpClient.newHttpClient();

    public String fetchUrl (String url, String method, Object params) throws IOException, InterruptedException {

        String uri = "http://localhost:8080/leilao";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();

        // library GSON
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();

        System.out.println("Itens cadastrados:");
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();

            // abaixo tenho que conhecer a estrutura do objeto retornado pelo rest,
            // posso ler da configuracao swagger ?

            long id = jsonObject.get("idItem").getAsLong();
            String nome = jsonObject.get("nomeItem").getAsString();
            Double valorRef = jsonObject.get("valorRef").getAsDouble();
            //
            System.out.println(id +" - " + nome + "- " + valorRef);
        }

        return null;
    }

    public String postUrl (String url, String method, Object params) throws IOException, InterruptedException {

        // posso pegar a URL e o método do arquivo de configuracao do swagger
        String uri = "http://localhost:8080/leilao";

        // formatar o objeto para requisicao - podemos faze-lo a partir do arq de configuracao
        JsonObject json = new JsonObject();
        //json.addProperty("idItem", nome);
        json.addProperty("nomeItem", "item 2 from client");
        json.addProperty("valorRef", 99.9);
        json.addProperty("valorMin", 59.0);
        json.addProperty("idCliente", 2l);

        //
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .method("POST",  HttpRequest.BodyPublishers.ofString(json.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        String responseBody = response.body();

        if (statusCode == 200) {
            System.out.println("Item de Leilao cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o item de leilao");
            System.out.println(responseBody);
        }
        return null;
    }

}
