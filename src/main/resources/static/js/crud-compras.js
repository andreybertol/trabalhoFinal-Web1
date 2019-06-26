function saveJson(urlDestino) {
    var compra = {
        id: ($("#id").val() != '' ? $("#id").val() : null),
        descricao: $("#descricao").val(),
        data_compra: $("#data_compra").val(),
        usuario: $("#usuario").val(),
        fornecedor: $("#fornecedor").val(),
        compraProduto: []
    }

    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        contentType: 'application/json',
        data: JSON.stringify(compra),
        success: function () {
            swal('Salvo!', 'Registro salvo com sucesso!', 'success');
            window.location = urlDestino;
        },
        error: function () {
            swal('Erro!', 'Falha ao salvar registro!', 'error');
        }
    });// Fim ajax
}

function editCompra(url) {
    $.get(url, function (entity, status) {
        $('#id').val(entity.id);
        $('#descricao').val(entity.descricao);
        $('#data_compra').val(entity.data_compra);
        $('#usuario').val(entity.usuario);
        $('#fornecedor').val(entity.fornecedor);
    });
    $('#modal-form-compra').modal();
}