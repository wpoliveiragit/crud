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

	interface UserDoc {
		interface Get {
			String SUM = "Lista todos os users.";
			String DES = "Retorna a lista de users.";
		}

		interface GetId {
			String SUM = "Retorna user/login.";
			String DES = "Retorna um user pelo login.";
		}

		interface Post {
			String SUM = "Add novo user.";
			String DES = "Adiciona um novo user na tabela.";
		}

		interface Put {
			String SUM = "Atualiza user.";
			String DES = "Atualiza um user existente pelo login.";
		}

		interface Delete {
			String SUM = "Remove user.";
			String DES = "Remove um user da tabela pelo ID.";
		}
	}

}
