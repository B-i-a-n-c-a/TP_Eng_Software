document.getElementById("btnSubmit").addEventListener("click", function () {
  const nome = document.getElementById("nome").value;
  const email = document.getElementById("email").value;
  const pass = document.getElementById("pass").value;

  console.log("Nome:", nome);
  console.log("Email:", email);
  console.log("Senha:", pass);

  localStorage.setItem("nome", nome);
  localStorage.setItem("email", email);
  localStorage.setItem("pass", pass);

  window.location.href = "page.html";
});
