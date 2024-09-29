package br.com.projeto.crud.swagger;

public interface ItensDocSwagger {
	interface Get {
		String sum = "Lista todos os itens.";
		String des = "Retorna uma lista contendo todos os itens.";
	}

	interface GetId {
		String sum = "Obtem item/ID.";
		String des = "Retorna um item pelo ID.";
	}

	interface Post {
		String sum = "Add novo item.";
		String des = "Adiciona um novo item na lista.";
	}

	interface Put {
		String sum = "Atualiza item.";
		String des = "Atualiza um item existente pelo ID.";
	}

	interface Delete {
		String sum = "Remove item.";
		String des = "Remove um item da lista pelo ID.";
	}
}