function editCompraProdutos(url) {
    $.get(url, function (entity, status) {
        $('#id').val(entity.id);
        $('#qtd').val(entity.qtd);
        $('#valor').val(entity.valor);
        $('#produto').val(entity.produto);
        $('#compra').val(entity.compra);
        $('#fornecedor').val(entity.fornecedor);
    });
    $('#modal-form-compra-produtos').modal();
}