package br.com.tdso;

import io.swagger.parser.OpenAPIParser;
import io.swagger.parser.SwaggerParser;
import io.swagger.models.Swagger;
import io.swagger.models.Path;
import io.swagger.models.HttpMethod;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.parser.core.models.ParseOptions;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

import java.util.Map;
import java.util.Set;

public class SwaggerTestGenerator {
    public static void main(String[] args) {
        // Carregar o arquivo Swagger
//        Swagger swagger = new SwaggerParser().read("/home/tdso/kdi/git/client-http/client-http/src/main/java/br/com/tdso/swagger.json");
//        System.out.println("swagger paths = " + swagger.toString());
//
//        // Iterar pelos endpoints e gerar testes
//        for (String pathName : swagger.getPaths().keySet()) {
//            Path path = swagger.getPath(pathName);
//            System.out.println("path => " + path);
//
//            for (HttpMethod method : path.getOperationMap().keySet()) {
//                // Crie testes para cada endpoint e método HTTP aqui
//                // Use uma biblioteca de teste como JUnit para criar os testes
//                System.out.println("method => " + method.toString());
//                System.out.println("path => " + path.getOperationMap().entrySet());
//            }
//        }
        // Caminho para o arquivo Swagger/OpenAPI 3.0.2
        String swaggerFilePath = "/home/tdso/kdi/git/client-http/client-http/src/main/java/br/com/tdso/swagger.json";

        // Configurar as opções de análise
        ParseOptions options = new ParseOptions();
        options.setResolve(true);

        // Realizar a análise do arquivo Swagger
        SwaggerParseResult parseResult = new OpenAPIParser().readLocation(swaggerFilePath, null, options);

        if (parseResult.getMessages().isEmpty()) {
            OpenAPI openAPI = parseResult.getOpenAPI();
            // Agora você pode trabalhar com o objeto OpenAPI e suas informações
            System.out.println("Título da API: " + openAPI.getInfo().getTitle());
            for (String path : openAPI.getPaths().keySet()){
                System.out.println("path = " + path);

                openAPI.getPaths().entrySet().forEach(stringPathItemEntry -> {
                    System.out.println("key = " + stringPathItemEntry.getKey());
                    System.out.println("value = " + stringPathItemEntry.getValue());
                    System.out.println("Tags = " + stringPathItemEntry.getValue().getGet().getTags());
                });
            }

        } else {
            // Lidar com erros de análise, se houver
            for (String message : parseResult.getMessages()) {
                System.err.println("Erro de análise: " + message);
            }
        }
    }
}

