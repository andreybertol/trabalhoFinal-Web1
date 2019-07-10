var compra = {
    "fornecedor": {},
    "descricao": String,
    "data_compra": String(),
    "usuario": {},
    "valorTotal": 0,
    "compraProdutos": new Array
};

function saveJsonCompra(urlDestino) {
    compra.fornecedor.id = $('#fornecedor option:selected').val();
    compra.usuario.id = $('#usuario option:selected').val();
    compra.descricao = $('#descricao').val();
    compra.data_compra = new Date().toLocaleDateString().split('/').reverse().join('-')

    $.ajax({
        method: 'POST',
        url: urlDestino,
        contentType: 'application/json',
        data    : JSON.stringify(compra),
        success: function () {
            swal({
                    title: 'Salvo!',
                    text: 'Registro salvo com sucesso!',
                    type: 'success',
                    showConfirmButton: false
                },
                setTimeout(function() {
                    window.location = "/compra/page";
                }, 1000));
        },
        error: function () {
            swal('Erro!', 'Falha ao salvar registro!', 'error');
        }
    });// Fim ajax
}

$('#quantidade').on('input', function (e) {
    var valor = Number($('#valor').val());
    var quantidade = Number($('#quantidade').val());

    var valorTotal = valor * quantidade;

    $('#valorTotal').val(valorTotal);
});


$('#produto').on('input', function (e) {
    var precoProduto = Number($('#produto option:selected').attr('valorProduto'));
    $('#valor').val(precoProduto);
});

function addCompraProduto() {

    var produtoLista = {};

    var nome = $('#produto option:selected').text();
    var quantidade = Number($('#quantidade').val());
    var valor = Number($('#valor').val());
    var produto = Number($('#produto').val());
    produtoLista.produto = {};
    produtoLista.produto.id = produto;
    produtoLista.quantidade = quantidade;
    produtoLista.valor = valor;

    compra.compraProdutos.push(produtoLista);

    var rowData = produtoLista;
    var rowStr = "<tr>"
        + "<td>" + produto + "</td>"
        + "<td>" + nome + "</td>"
        + "<td>" + rowData.valor + "</td>"
        + "<td>" + rowData.quantidade + "</td>"
        + "<td>" + rowData.valor*rowData.quantidade + "</td>"
        + "</tr>"
    $("#tableProdutos tbody").append(rowStr);

    $('#modal-form-produto').modal('hide');

}

function editCompra(url) {
    $.get(url, function (entity, status) {
        $('#id').val(entity.id);
        $('#descricao').val(entity.descricao);
        $('#data_compra').val(formatDate(entity.data_compra));
        $('#usuario').val(entity.usuario);
        $('#fornecedor').val(entity.fornecedor);
    });
    $('#modal-form').modal();
}

function formatDate(inputFormat) {
    function pad(s) {
        return (s < 10) ? '0' + s : s;
    }

    var d = new Date(inputFormat);
    return [d.getFullYear(), pad(d.getMonth() + 1), pad(d.getDate())].join('-');
}