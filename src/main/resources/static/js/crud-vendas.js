var venda = {
    "data_venda": String(),
    "usuario": {},
    "valorTotal": 0,
    "vendaProdutos": new Array
};

 $(document).ready(function(){
     $('#nomeProduto').val(localStorage.getItem("nome"));
 });


function produtoDetalhe(url){

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
        data    : JSON.stringify(venda),
        success: function () {
            swal('Salvo!', 'Registro salvo com sucesso!', 'success');
            window.location = '/venda/page';
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

function addProdutoCart() {

    var produtoLista = {};

    var nome = $('#produto option:selected').text();
    var quantidade = Number($('#quantidade').val());
    var valor = Number($('#valor').val());
    var produto = Number($('#produto').val());
    produtoLista.produto = {};
    produtoLista.produto.id = produto;
    produtoLista.quantidade = quantidade;
    produtoLista.valor = valor;

    venda.vendaProdutos.push(produtoLista);
}

function editVenda(url) {
    $.get(url, function (entity, status) {
        $('#id').val(entity.id);
        $('#data_venda').val(formatDate(entity.data_venda));
        $('#usuario').val(entity.usuario);
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