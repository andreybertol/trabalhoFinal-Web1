var venda = {
    "data_venda": String(),
    "usuario": {},
    "valorTotal": 0,
    "vendaProdutos": new Array
};

$(document).ready(function () {
    $('#nomeProduto').val(localStorage.getItem("nome"));
});


function produtoDetalhe(url) {

    $.get(url, function (entity, status) {
        localStorage.id = entity.id;
        localStorage.nome = entity.nome;
        localStorage.detalhes = entity.detalhes;
        localStorage.categoria = entity.categoria;
        localStorage.marca = entity.marca;
    });
    window.location = '/produto/detalhe';
}

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

$('#btnLimpar').on('click', function (e) {
    venda.vendaProdutos.length = 0;
    venda = [];
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

            venda.vendaProdutos.push(produtosCarrinho);

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
    // impedir informar quantidade negativa
    if (Number($('#quantidade').val() <= 0)) {
        $('#quantidade').val(0);
    }
});

function formatDate(inputFormat) {
    function pad(s) {
        return (s < 10) ? '0' + s : s;
    }

    var d = new Date(inputFormat);
    return [d.getFullYear(), pad(d.getMonth() + 1), pad(d.getDate())].join('-');
}