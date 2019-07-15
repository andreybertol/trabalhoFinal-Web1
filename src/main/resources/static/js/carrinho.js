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
        // valida se foi informada quantidade do produto
        if (Number($('#quantidade').val() > 0)) {

            var produtoID = Number($("#produtoID").val());
            var nome = $("#produtoNome").val();
            var quantidade = Number($("#quantidade").val());
            var valor = $("#valorProduto").val();

            var carrinho = JSON.parse(localStorage.getItem("produtosCarrinho"));

            // valida se já foi adicionado algum item ao localstorage
            if (carrinho == null) {
                var carrinho = new Array;
                var produtosCarrinho = {};
                produtosCarrinho.produto = {};
                produtosCarrinho.produto.id = produtoID;
                produtosCarrinho.nome = nome;
                produtosCarrinho.quantidade = quantidade;
                produtosCarrinho.valor = valor;

                carrinho.push(produtosCarrinho);
            } else {
                var produtoExistente;

                for (i = 0; i < carrinho.length; i++) {
                    if (carrinho[i].produto.id == produtoID) {
                        produtoExistente = true;
                        indiceProduto = i;
                        break;
                    }
                }

                if (produtoExistente) {
                    carrinho[i].quantidade += quantidade;
                } else {
                    var produtosCarrinho = {};

                    produtosCarrinho.produto = {};
                    produtosCarrinho.produto.id = produtoID;
                    produtosCarrinho.nome = nome;
                    produtosCarrinho.quantidade = quantidade;
                    produtosCarrinho.valor = valor;

                    carrinho.push(produtosCarrinho);
                }
            }

            localStorage.setItem("produtosCarrinho", JSON.stringify(carrinho));

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

$('#quantidade').on('input', function (e) {
    var valor = Number($('#valor').val());
    var quantidade = Number($('#quantidade').val());

    var valorTotal = valor * quantidade;

    $('#valorTotal').val(valorTotal);
});