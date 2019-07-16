function editFornecedor(url){

    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#nome').val(entity.nome);
        $('#endereco').val(entity.endereco);
        $('#cnpj').val(entity.cnpj);

    });
    $('#modal-form').modal();
}