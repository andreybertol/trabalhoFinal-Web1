// $('#btnCarrinho').on('click', function (e) {
//     window.location = "/checkout"
// });

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