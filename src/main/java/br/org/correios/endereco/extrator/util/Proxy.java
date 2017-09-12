package br.org.correios.endereco.extrator.util;

/**
 * <p>Configura o proxy de rede.</p>
 * 
 * @author thiago-amm
 * @version v1.0.0 12/09/2017
 * @since v1.0.0
 */
public final class Proxy {

    public static void configure() {
        System.out.println("Configurando o proxy");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.getProperties().put("http.proxyHost", "10.243.1.3");
        System.getProperties().put("http.proxyPort", "2303");
        System.setProperty("https.proxyHost", "10.243.1.3");
        System.setProperty("https.proxyPort", "2303");
    }
}
