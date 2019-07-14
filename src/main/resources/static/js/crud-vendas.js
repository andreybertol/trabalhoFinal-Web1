var venda = {
    "data_venda": String(),
    "usuario": {},
    "valorTotal": 0,
    "vendaProdutos": new Array
};

// assim que carregar a página, cria os elementos se houver produtos inseridos no carrinho
window.onload = function () {

    var carrinho = JSON.parse(localStorage.getItem("produtosCarrinho"));

    // testar isempty direito
    for (i = 0; i < carrinho.length; i++) {
        var rowData = carrinho[i];

        var rowStr = "<tr>"
            // + "<td>" + rowData.imagem + "</td>"
            + "<td class=\"text-center\">" + rowData.produto + " - " + rowData.nome + "</td>"
            // + "<td>" + rowData.nome + "</td>"
            + "<td class=\"text-center\">" + rowData.quantidade + "</td>"
            + "<td class=\"text-center\">" + Number(rowData.valor * rowData.quantidade) + "</td>"
            + "<td class=\"text-center\">"
            + "<a id=\"btnRemover\" " +
            " class=\"btn btn-danger btn-xs\">"
            + "<i class=\"fa fa-trash\">" + "</i>"
            + "</a>" + "</td>"
            + "</tr>"
        $("#tabela-produtos tbody").append(rowStr);
    }

    if (carrinho.length > 0) {
        // adiciona botão finalizar
        var btnFinalizar = "<div class=\"col-xs-4\">"
            + "<a id=\"btnFinalizar\" class=\"btn btn-primary\">"
            + "<i class=\"fa fa-plus-square\"></i> Finalizar Compra"
            + "</a>"
            + "</div>"

        $("#frmFinalizar").append(btnFinalizar);
    }
};

$("#btnFinalizar").on('click', function (e) {
    swal({
        title: 'Salvo!',
        text: 'Compra realizada com sucesso!',
        type: 'success',
        showConfirmButton: false
    });
});

function saveJsonVenda(urlDestino) {
    venda.fornecedor.id = $('#fornecedor option:selected').val();
    venda.usuario.id = $('#usuario option:selected').val();
    venda.descricao = $('#descricao').val();
    venda.data_venda = new Date().toLocaleDateString().split('/').reverse().join('-')

    $.ajax({
        method: 'POST',
        url: urlDestino,
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

$("#btnRemover").on('input', function (e) {
    carrinho = localStorage.getItem("produtosCarrinho");

    for (var i = 0; i <= carrinho.length; i++) {

    }
});

function formatDate(inputFormat) {
    function pad(s) {
        return (s < 10) ? '0' + s : s;
    }

    var d = new Date(inputFormat);
    return [d.getFullYear(), pad(d.getMonth() + 1), pad(d.getDate())].join('-');
}