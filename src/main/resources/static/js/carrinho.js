// $('#btnCarrinho').on('click', function (e) {
//     window.location = "/checkout"
// });

// mostra ou esconde botão do carrinho se houver ou não item
$('#btnInserir').on('click', function (e) {
    var qtd = Number($('#quantidade').val());

    if (qtd != 0) {
        if (e.style.display == 'block') {
            e.style.display = 'none';
        } else {
            e.style.display = 'block';
        }
    }
});

$('#btnLimpar').on('click', function (e) {
    swal({
            title: "Atenção!",
            text: "Deseja limbar o carrinho?",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Sim",
            closeOnConfirm: false
        },
        function () {
            localStorage.clear();
            swal(
                {
                    title: "Atenção!",
                    text: "Seu carrinho está limpo.",
                    timer: 1500,
                    type: "success"
                });
        });
});