// $('#btnCarrinho').on('click', function (e) {
//     window.location = "/checkout"
// });

// mostra ou esconde botão do carrinho se houver ou não item
// $('#btnInserir').on('click', function (e) {
//     var qtd = Number($('#quantidade').val());
//
//     if (qtd != 0) {
//         if (e.style.display == 'block') {
//             e.style.display = 'none';
//         } else {
//             e.style.display = 'block';
//         }
//     }
// });

listaProduto = new Array;

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

$('#btnInserir').on('click', function (e) {
        if (Number($('#quantidade').val() > 0)) {

            produtosCarrinho = {};

            var produto = Number($("#produtoID").val());
            var quantidade = Number($("#quantidade").val());
            var totalValor = $("#valorProduto").val();
            produtosCarrinho.produto = produto;
            produtosCarrinho.quantidade = quantidade;
            produtosCarrinho.totalValor = totalValor;

            listaProduto.push(produtosCarrinho);

            localStorage.setItem("produtosCarrinho", JSON.stringify(listaProduto));

            swal({
                    title: 'Salvo!',
                    text: 'Produto adicionado!',
                    type: 'success',
                    showConfirmButton: false
                },
                setTimeout(function () {
                    window.location = "/produto/page";
                }, 1500));

        } else {
            swal('Erro!', 'Informe a quantidade!', 'warning');
        }
    }
);