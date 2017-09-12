package br.org.correios.endereco.extrator.model;

import jedi.db.models.CharField;
import jedi.db.models.Manager;
import jedi.db.models.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Define um endereço a ser persistido no banco de dados.</p>
 * 
 * @author thiago-amm
 * @version v1.0.0 12/09/2017
 * @since v1.0.0
 * 
 * @see jedi.db.models.Model
 * @see jedi.db.models.Manager
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Endereco extends Model {
   
   private static final long serialVersionUID = -4075356689244543263L;
   
   @CharField(max_length = 255)
   private String logradouro;
   
   @CharField(max_length = 255)
   private String bairro;
   
   @CharField(max_length = 255)
   private String localidade;
   
   @CharField(max_length = 2)
   private String uf;
   
   @CharField(max_length = 9)
   private String cep;
   
   public static Manager objects = new Manager(Endereco.class);
   
   public String toString() {
      return String.format("%s | %s | %s | %s | %s", logradouro, bairro, localidade, uf, cep);
   }
}
