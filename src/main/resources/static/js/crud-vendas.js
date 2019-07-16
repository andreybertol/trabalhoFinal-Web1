// assim que carregar a página, cria os elementos se houver produtos inseridos no carrinho
window.onload = function () {

    var carrinho = JSON.parse(localStorage.getItem("produtosCarrinho"));

    for (i = 0; i < carrinho.length; i++) {
        var rowData = carrinho[i];

        var rowStr = "<tr>"
            + "<td class=\"text-center\">" + rowData.produto.id + " - " + rowData.nome + "</td>"
            + "<td class=\"text-center\">"
            + "<input class=\"text-center\" id=\"input\" type=\"number\" onchange=ajustarQtd($(this).attr(\"id\"))>"
            + "</td>"
            + "<td class=\"text-center\" id=\"vlr\">" + Number(rowData.valor * rowData.quantidade) + "</td>"
            + "<td class=\"text-center\">"
            + "<a class=\"btn btn-danger btn-xs\" id=\"btnRemover\" onclick=removerItem($(this).attr(\"id\"))>"
            + "<i class=\"fa fa-trash\">" + "</i>"
            + "</a>"
            + "</td>"
            + "</tr>"

        $("#tabela-produtos tbody").append(rowStr);

        $("#input").val(rowData.quantidade);
        $("#input").attr('id', rowData.produto.id);
        $("#btnRemover").attr('id', rowData.produto.id);
        $("#vlr").attr('id', "vlr_" + rowData.produto.id);
    }

    // adiciona botão finalizar
    if (carrinho.length > 0) {
        var dropPagamento = " <div class=\"form-group\" style=\"text-align: center; width: 342px\">"
            + "<label for=\"pgto\">Formas de Pagamento</label>"
            + "<select id=\"pgto\" name=\"pgto\"class=\"form-control\">"
            + " <option value=\"Dinheiro\">Dinheiro</option>"
            + " <option value=\"Boleto\">Boleto</option>"
            + " <option value=\"Paypal\">Paypal</option>"
            + " </select>"
            + " </div>"

        var btnFinalizar = "<div class=\"col-xs-4\">"
            + "<a sec:authorize=\"isAuthenticated()\" "
            + "onclick=\"saveJsonVenda('/venda/json')\" class=\"btn btn-primary\">"
            + "<i class=\"fa fa-plus-square\"></i> Finalizar Compra"
            + "</a>"
            + "</div>"

        $("#frmFinalizar").append(dropPagamento);
        $("#frmFinalizar").append(btnFinalizar);
    }
};

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
            if (produtoID == carrinho[i].produto.id) {

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

function ajustarQtd(produtoID) {
    var carrinho = JSON.parse(localStorage.getItem("produtosCarrinho"));
    var qtdAtual = Number($('#' + produtoID).val());

    if (qtdAtual < 0) {
        qtdAtual = 0;
    }

    for (var i = 0; i < carrinho.length; i++) {
        if (produtoID == carrinho[i].produto.id) {

            carrinho[i].quantidade = qtdAtual;
            $('#vlr_' + produtoID).text(carrinho[i].valor * qtdAtual);

            localStorage.setItem("produtosCarrinho", JSON.stringify(carrinho));
        }
    }
}

function saveJsonVenda(urlDestino) {
    var venda = {
        "usuario": {},
        "data_venda": String,
        "valor_total": 0,
        "forma_pgto": String,
        "vendaProdutos": new Array
    };

    // if (Number($('#pgto').val()) == 0) {
    //     swal('Alerta!','Informe a Forma de Pagamento!','alert');
    //
    //     return
    // }

    venda.data_venda = new Date().toLocaleDateString().split('/').reverse().join('-')

    var carrinho = JSON.parse(localStorage.getItem("produtosCarrinho"));

    for (i = 0; i < carrinho.length; i++) {
        venda.vendaProdutos.push(carrinho[i]);
    }

    for (i = 0; i < carrinho.length; i++) {
        venda.valor_total += Number(carrinho[i].valor * carrinho[i].quantidade);
    }

    venda.forma_pgto = $('#pgto').val();

    $.ajax({
        method: 'POST',
        url: "/venda/json",
        contentType: 'application/json',
        data: JSON.stringify(venda),
        success: function () {
            swal({
                    title: 'Finalizada!',
                    text: 'Venda finalizada com sucesso!',
                    type: 'success',
                    showConfirmButton: false
                },
                setTimeout(function () {
                    localStorage.clear();
                    window.location = "/home";
                }, 800));
        },
        error: function () {
            swal('Erro!', 'Falha ao finalizar venda!', 'error');
        }
    });// Fim ajax
}

function formatDate(inputFormat) {
    function pad(s) {
        return (s < 10) ? '0' + s : s;
    }

    var d = new Date(inputFormat);
    return [d.getFullYear(), pad(d.getMonth() + 1), pad(d.getDate())].join('-');
}