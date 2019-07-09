function clearForm(){
    $('#id').val('');
    $('#nome').val('');
    $('#username').val('');
    $('#password').val('');
    $('#frm input:checkbox:checked').each(function(){
        $(this).prop('checked', false);
    });
}

function editUsuario(url){
    clearForm();
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#nome').val(entity.nome);
        $('#username').val(entity.username);
        $.each(entity.permissoes, function (i, item){
            $('#chk_'+entity.permissoes[i].id).prop('checked', true);
        })
    });

    // localStorage.setItem.usuarioID(entity.id);
    // localStorage.setItem.usuarioNome(entity.nome);
    //
    // function setarNomeUsuario(){
    //     $('#nomeUsuario').innerHTML = localStorage.usuarioNome;
    // };

    $('#modal-form').modal();
}