const detalhesItensCardapio = {
  "☕ Café Expresso": {
    descricao:
      "Um café forte e concentrado, preparado com água quente pressurizada que passa rapidamente pelo café moído. Tem um sabor intenso e é a base para a maioria das outras bebidas de café.",
    imagem: "images/café-expresso.jpg",
    preco: "R$ 5,00",
  },
  "☕ Macchiato": {
    descricao:
      "Um espresso 'manchado' com uma pequena quantidade de espuma de leite por cima. É uma opção mais suave em comparação ao espresso puro.",
    imagem: "images/macchiato.jpg",
    preco: "R$ 7,00",
  },
  "☕ Café Americano": {
    descricao:
      "Um café espresso diluído em água quente, proporcionando uma bebida mais suave e menos concentrada do que o espresso.",
    imagem: "images/café-americano.jpg",
    preco: "R$ 6,00",
  },
  "☕ Café Latte": {
    descricao:
      "Feito com uma dose de espresso e bastante leite vaporizado, finalizado com uma camada fina de espuma. É uma bebida cremosa e levemente doce.",
    imagem: "images/café-latte2.jpg",
    preco: "R$ 9,00",
  },
  "☕ Capuccino": {
    descricao:
      "Um equilíbrio perfeito de espresso, leite vaporizado e espuma, geralmente finalizado com um toque de canela ou chocolate em pó",
    imagem: "images/capuccino.jpg",
    preco: "R$ 8,00",
  },
  "☕ Mocha": {
    descricao:
      "Uma mistura deliciosa de café espresso, chocolate quente e leite vaporizado, geralmente finalizado com chantilly por cima.",
    imagem: "images/mocha.jpg",
    preco: "R$ 10,00",
  },
  "🧋 Café Gelado": {
    descricao:
      "Café preparado e servido frio, muitas vezes com gelo. Pode ser adoçado e misturado com leite ou sabores adicionais.",
    imagem: "images/café-gelado.jpg",
    preco: "R$ 8,00",
  },
  "🧋 Cold Brew": {
    descricao:
      "Café feito através da infusão do pó de café em água fria por um longo período de tempo, resultando em um sabor suave e menos ácido.",
    imagem: "images/cold-brew.jpg",
    preco: "R$ 12,00",
  },
  "🧋 Café Affogatto": {
    descricao:
      "Bola de sorvete coberta com um shot de café expresso quente. Uma sobremesa simples, mas sofisticada, onde uma dose quente de espresso é derramada sobre uma bola de sorvete, geralmente de baunilha.",
    imagem: "images/café-affogatto.jpg",
    preco: "R$ 14,00",
  },
  "🧋 Café Frappé": {
    descricao:
      "Uma bebida gelada e cremosa, preparada com café, gelo e um toque de leite ou chantilly, frequentemente adoçada com xaropes de sabores.",
    imagem: "images/frappé.jpg",
    preco: "R$ 11,00",
  },
};

function adicionarItensCardapio(itens) {
  const container = document.getElementById("itens-cardapio");
  itens.forEach((item) => {
    const divItem = document.createElement("div");
    divItem.className = "item-cardapio";
    divItem.textContent = item;

    divItem.addEventListener("click", () => {
      mostrarDetalhesItem(item);
    });

    container.appendChild(divItem);
  });
}

function mostrarDetalhesItem(item) {
  const detalhes = detalhesItensCardapio[item];
  if (detalhes) {
    document.getElementById("nome-produto-modal").textContent = item;
    document.getElementById("foto-produto-modal").src = detalhes.imagem;
    document.getElementById("descricao-produto-modal").textContent =
      detalhes.descricao;
    document.getElementById("preco-produto-modal").textContent = detalhes.preco;

    const modal = document.getElementById("modal");
    modal.style.display = "block";
  }
}

window.onload = function () {
  const modal = document.getElementById("modal");
  modal.style.display = "none";
};

document.getElementById("fechar-modal").onclick = function () {
  const modal = document.getElementById("modal");
  modal.style.display = "none";
};

window.onclick = function (event) {
  const modal = document.getElementById("modal");
  if (event.target === modal) {
    modal.style.display = "none";
  }
};

const itensCardapio = [
  "☕ Café Expresso",
  "☕ Macchiato",
  "☕ Café Americano",
  "☕ Café Latte",
  "☕ Capuccino",
  "☕ Mocha",
  "☕ Café Gelado",
  "☕ Cold Brew",
  "☕ Café Affogatto",
];

const dataList = document.getElementById("produtos-lista");

itensCardapio.forEach((item) => {
  const option = document.createElement("option");
  option.value = item;
  dataList.appendChild(option);
});

adicionarItensCardapio(itensCardapio);

document.querySelector(".enviar-button").addEventListener("click", function () {
  const produtoSelecionado = document.querySelector(
    '[list="produtos-lista"]'
  ).value;
  const quantidade = document.querySelector(
    'input[placeholder="Quantidade"]'
  ).value;
  const tipo = document.querySelector(
    'input[placeholder="Tipo do produto"]'
  ).value;

  if (!produtoSelecionado || !quantidade) {
    alert("Por favor, selecione um produto e insira a quantidade.");
    return;
  }

  const detalhes = detalhesItensCardapio[produtoSelecionado];
  const precoUnitario = parseFloat(
    detalhes.preco.replace("R$", "").replace(",", ".")
  );

  const totalPreco = precoUnitario * quantidade;

  const carrinho = JSON.parse(localStorage.getItem("carrinho")) || [];

  carrinho.push({
    tipo: tipo,
    produto: produtoSelecionado,
    quantidade: quantidade,
    preco: `R$ ${totalPreco.toFixed(2).replace(".", ",")}`,
  });
  localStorage.setItem("carrinho", JSON.stringify(carrinho));
  window.location.href = "carrinho.html";
});
