document.addEventListener('DOMContentLoaded', function() {

  const btnAbrirModal2 = document.getElementById('btnAbrirModal2');
  btnAbrirModal2.addEventListener('click', function() {
    const modal = new bootstrap.Modal('#modalExemplo2');
    modal.show();
  });

  setTimeout(() => {
    const modal = new bootstrap.Modal('#modalExemplo3');
    modal.show();
  }, 2000);
});