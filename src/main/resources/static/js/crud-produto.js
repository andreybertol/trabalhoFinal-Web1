// $(document).ready(function(){
//     const
// });

var produto =
    {
        "id": Number,
        "nome": String,
        "detalhes": String,
        "valor": Number,
        "estoque": Number,
        "categoria": {},
        "marca": {},
    };

function comprarProduto(url) {
    $.get(url, function (entity, status) {
        produto.id = $('#id').val();
        // produto.nome = entity.nome;
        // produto.detalhes = entity.detalhes;
        // produto.valor = entity.valor;
        // produto.estoque = entity.estoque;
        // produto.categoria = entity.categoria;
        // produto.marca = entity.marca;

        // $('#id').val(entity.id);
        // $('#nome').val(entity.nome);
        // $('#detalhes').val(entity.detalhes);
        // $('#valor').val(entity.valor);
        // $('#estoque').val(entity.estoque);
        // $('#categoria').val(entity.categoria.id);
        // $('#marca').val(entity.marca.id);
    });

    window.location = '/produto/comprar';
}

function editProduto(url) {
    $.get(url, function (entity, status) {
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

function saveUpload(urlDestino) {
    var formData = new FormData($('#frm')[0]);
    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function () {
            swal('Salvo!', 'Registro salvo com sucesso!', 'success');
            window.location = urlDestino;
        },
        error: function () {
            swal('Errou!', 'Falha ao salvar o registro!', 'error');
        }
    });//Fim Ajax
}