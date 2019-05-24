function editProduto(url){
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#nome').val(entity.nome);
        $('#detalhes').val(entity.detalhes);
        $('#valor').val(entity.valor);
        $('#estoque').val(entity.estoque);
        $('#categoria').val(entity.categoria.id);
        $('#marca').val(entity.marca.id);

    });
    $('#modal-form').modal();
}