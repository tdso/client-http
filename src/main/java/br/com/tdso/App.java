package br.com.tdso;

import br.com.tdso.service.ClientHttp;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Iniciando Requisicao .... " );
        ClientHttp clientHttp = new ClientHttp();
        fetch(clientHttp);
        post(clientHttp);
        fetch(clientHttp);

    }
    private static void fetch (ClientHttp clientHttp){
        try {
            clientHttp.fetchUrl(null,null,null);
        } catch (IOException |  InterruptedException e) {
            System.out.println("Erro > catch > fetch");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    private static void post(ClientHttp clientHttp){
        try {
            clientHttp.postUrl(null, null, null);
        } catch (IOException |  InterruptedException e) {
            System.out.println("Erro > catch > post");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
