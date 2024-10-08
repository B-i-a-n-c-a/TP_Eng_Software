// Função para adicionar itens ao cardápio
function adicionarItensCardapio(itens) {
  const container = document.getElementById("itens-cardapio");
  itens.forEach((item) => {
    const divItem = document.createElement("div");
    divItem.className = "item-cardapio";
    divItem.textContent = item; // Define o texto do item
    container.appendChild(divItem); // Adiciona o item ao container
  });
}

// Define os itens do cardápio
const itensCardapio = [
  "🍅 Ketchup",
  "🍯 Maionese",
  "🌭 Mostarda",
  "Queijo",
  "Cafe",
];

// Chama a função para adicionar os itens ao cardápio
adicionarItensCardapio(itensCardapio);
