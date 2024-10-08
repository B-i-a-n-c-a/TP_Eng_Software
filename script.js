// Adiciona um evento de clique ao botão "Enviar!"
document.getElementById("btnSubmit").addEventListener("click", function () {
  // Captura os valores dos campos de entrada
  const nome = document.getElementById("nome").value;
  const email = document.getElementById("email").value;
  const pass = document.getElementById("pass").value;

  // Exibe os valores no console
  console.log("Nome:", nome);
  console.log("Email:", email);
  console.log("Senha:", pass);

  localStorage.setItem("nome", nome);
  localStorage.setItem("email", email);
  localStorage.setItem("pass", pass);

  window.location.href = "page.html";
});
