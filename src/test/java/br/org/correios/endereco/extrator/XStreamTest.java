package br.org.correios.endereco.extrator;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.org.correios.endereco.extrator.model.Endereco;

/**
 * <p>Testa a conversão de objeto Java para XML.</p>
 * 
 * @author thiago-amm
 * @version v1.0.0 12/09/2017
 * @since v1.0.0
 *
 */
public class XStreamTest {

    @Test
    public void test() {
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua 259");
        endereco.setLocalidade("Goiânia");
        endereco.setBairro("Setor Leste Universitário");
        endereco.setCep("74610-230");
        endereco.setUf("GO");
        XStream xstream = new XStream();
        xstream.alias("endereco", Endereco.class);
        System.out.println(xstream.toXML(endereco));
    }

}
