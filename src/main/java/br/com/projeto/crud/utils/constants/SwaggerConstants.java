package br.com.projeto.crud.utils.constants;

public interface SwaggerConstants {

	interface ItensDoc {
		interface Get {
			String SUM = "Lista todos os itens.";
			String DES = "Retorna uma lista contendo todos os itens.";
		}

		interface GetId {
			String SUM = "Obtem item/ID.";
			String DES = "Retorna um item pelo ID.";
		}

		interface Post {
			String SUM = "Add novo item.";
			String DES = "Adiciona um novo item na lista.";
		}

		interface Put {
			String SUM = "Atualiza item.";
			String DES = "Atualiza um item existente pelo ID.";
		}

		interface Delete {
			String SUM = "Remove item.";
			String DES = "Remove um item da lista pelo ID.";
		}
	}
	
}
