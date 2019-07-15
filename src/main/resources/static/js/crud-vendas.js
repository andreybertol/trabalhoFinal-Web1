var venda = {
    "dataVenda": String(),
    "usuario": {},
    "valorTotal": 0,
    "vendaProdutos": new Array
};

// assim que carregar a página, cria os elementos se houver produtos inseridos no carrinho
window.onload = function () {

    var carrinho = JSON.parse(localStorage.getItem("produtosCarrinho"));

    for (i = 0; i < carrinho.length; i++) {
        var rowData = carrinho[i];

        var rowStr = "<tr id=\"row\">"
            + "<td class=\"text-center\">" + rowData.produto + " - " + rowData.nome + "</td>"
            + "<td class=\"text-center\">" + rowData.quantidade + "</td>"
            + "<td class=\"text-center\">" + Number(rowData.valor * rowData.quantidade) + "</td>"
            + "<td class=\"text-center\">"
            + "<a class=\"btn btn-danger btn-xs\" id=\"btnRemover\" onclick=removerItem($(this).attr(\"id\"))>"
            + "<i class=\"fa fa-trash\">" + "</i>"
            + "</a>"
            + "</td>"
            + "</tr>"

        $("#tabela-produtos tbody").append(rowStr);
        // $("#row").attr('id', rowData.produto);
        $("#btnRemover").attr('id', rowData.produto);
    }

    if (carrinho.length > 0) {
        // adiciona botão finalizar
        var btnFinalizar = "<div class=\"col-xs-4\">"
            + "<a sec:authorize=\"isAuthenticated()\" "
            + "onclick=\"saveJsonVenda()\" class=\"btn btn-primary\">"
            + "<i class=\"fa fa-plus-square\"></i> Finalizar Compra"
            + "</a>"
            + "</div>"

        $("#frmFinalizar").append(btnFinalizar);
    }
};

function saveJsonVenda() {
    venda.dataVenda = new Date().toLocaleDateString().split('/').reverse().join('-')
    // venda.usuario.id = $('#usuario option:selected').val();
    // venda.valorTotal = $('#descricao').val();

    $.ajax({
        method: 'POST',
        url: "/venda/json",
        contentType: 'application/json',
        data: JSON.stringify(venda),
        success: function () {
            swal({
                    title: 'Salvo!',
                    text: 'Registro salvo com sucesso!',
                    type: 'success',
                    showConfirmButton: false
                },
                setTimeout(function () {
                    window.location = "/venda/page";
                }, 1000));
        },
        error: function () {
            swal('Erro!', 'Falha ao salvar registro!', 'error');
        }
    });// Fim ajax
}

function removerItem(produtoID) {

    swal({
        title: "Confirma a remoção do registro?",
        text: "Esta ação não poderá ser desfeita!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        cancelButtonText: "Cancelar",
        confirmButtonText: "Remover",
        closeOnConfirm: false
    }, function () {

        var carrinho = JSON.parse(localStorage.getItem("produtosCarrinho"));

        for (var i = 0; i < carrinho.length; i++) {
            if (produtoID == carrinho[i].produto) {

                carrinho.splice(i, 1);

                localStorage.setItem("produtosCarrinho", JSON.stringify(carrinho));

                swal({
                        title: 'Salvo!',
                        text: 'Registro salvo com sucesso!',
                        type: 'success',
                        showConfirmButton: false
                    },
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000));
            }
        }
    });
};

function formatDate(inputFormat) {
    function pad(s) {
        return (s < 10) ? '0' + s : s;
    }

    var d = new Date(inputFormat);
    return [d.getFullYear(), pad(d.getMonth() + 1), pad(d.getDate())].join('-');
}