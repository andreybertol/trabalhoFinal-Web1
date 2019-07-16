"use strict";
function remove(id, url) {
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
            var destino = url + '/' + id;
            $.ajax({
                type: 'DELETE',
                url: destino,
                success: function () {
                    $('#row_' + id).remove();
                    swal('Removido!','Registro removido com sucesso!','success');
                },
                error: function () {
                    swal('Erro!',
                        'Falha ao remover registro!',
                        'error');

                }
            });//Fim ajax
        }
    );//FIM SWAWL
}

function save(urlDestino) {
    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        data: $('#frm').serialize(),
        success: function () {
            swal({
                title: 'Salvo!',
                text: 'Registro salvo com sucesso!',
                type: 'success',
                showConfirmButton: false
                },
                setTimeout(function() {
                    window.location = urlDestino;
                }, 800));
    },
    error: function () {
        swal("Errou!", 'Falha ao salvar o registro!', 'error');
    }
    }); // fim ajax
}

function edit(url){
    $.get(url, function(entity, status){
      $('#id').val(entity.id);
      //document.getElementById('id')
      $('#nome').val(entity.nome);
    });
    $('#modal-form').modal();
}