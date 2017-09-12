package br.org.correios.endereco.extrator;

import java.io.File;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.BeforeClass;
import org.junit.Test;

import br.org.correios.endereco.extrator.model.Endereco;
import br.org.correios.endereco.extrator.util.ProxyUtil;
import jedi.db.engine.JediEngine;

/**
 * <p>
 * EXTRATOR DE ENDEREÇOS (CORREIOS)
 *
 * Extrai endereços no site dos CORREIOS a partir do CEP informado.
 * 
 * Faixa(s) de CEP da UF: GO
 * 
 * 72800-000 a 72999-999
 * 73700-000 a 76799-999
 * 
 * Faixa(s) de CEP da Localidade: Goiânia
 * 
 * 74000-001 a 74899-999
 * </p>
 *
 * @author thiago-amm
 * @version v1.0.0 12/09/2017
 * @since v1.0.0
 */
public class CorreiosTest {
   
   @BeforeClass
   public static void setup() {
      JediEngine.FOREIGN_KEY_CHECKS = false;
   }
   
   @Test
   public void test() {
      try {
         File file = new File(System.getProperty("user.dir") + File.separator + "enderecos.txt");
         PrintWriter pw = new PrintWriter(file);
         ProxyUtil.configure();
         System.out.println();
         System.out.println("###################################################################");
         System.out.println("#   LOGRADOURO   |   BAIRRO   |   LOCALIDADE   |   UF   |   CEP   #");
         System.out.println("###################################################################");
         for (long numero = 74212832; numero < 74700000; numero++) {
            Document document = Jsoup
                  .connect("http://www.buscacep.correios.com.br/servicos/dnec/consultaLogradouroAction.do")
                  .data("CEP", "" + numero)
                  .data("Metodo", "listaLogradouro")
                  .data("TipoConsulta", "cep")
                  .data("StartRow", "1")
                  .data("EndRow", "10")
                  .timeout(360 * 1000)
                  .post();
            Thread.sleep(25);
            Elements table = document
                  .getAllElements()
                  .select("div.ctrlcontent div table[bgcolor=gray]");
            System.out.printf("Número: %d - ", numero);
            if (table.isEmpty()) {
               System.out.println("Nenhum registro encontrado.");
            } else {
               Elements rows = table.select("td");
               Endereco endereco = new Endereco();
               endereco.setLogradouro(rows.eq(0).text());
               endereco.setBairro(rows.eq(1).text());
               endereco.setLocalidade(rows.eq(2).text());
               endereco.setUf(rows.eq(3).text());
               endereco.setCep(rows.eq(4).text());
               endereco.save();
               System.out.println(endereco);
               pw.println(endereco);
               pw.flush();
            }
         }
         pw.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
