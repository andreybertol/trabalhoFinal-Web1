var venda = {
    "data_venda": String(),
    "usuario": {},
    "valorTotal": 0,
    "vendaProdutos": new Array
};

$(document).ready(function(){
    swal('teste', 'abriu saporra', 'success');
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

function formatDate(inputFormat) {
    function pad(s) {
        return (s < 10) ? '0' + s : s;
    }

    var d = new Date(inputFormat);
    return [d.getFullYear(), pad(d.getMonth() + 1), pad(d.getDate())].join('-');
}