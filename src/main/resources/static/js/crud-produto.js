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
            swal({
                    title: 'Salvo!',
                    text: 'Registro salvo com sucesso!',
                    type: 'success',
                    showConfirmButton: false
                },
                setTimeout(function() {
                    window.location = urlDestino;
                }, 1000));
        },
        error: function () {
            swal('Errou!', 'Falha ao salvar o registro!', 'error');
        }
    });//Fim Ajax
}

$('#btnInserir').on('click', function (e) {
        if (Number($('#quantidade').val() > 0)) {

            produtosCarrinho = {};

            var produto = Number($("#produtoID").val());
            var quantidade = Number($("#quantidade").val());
            var totalValor = $("#valorProduto").val();
            produtosCarrinho.produto = produto;
            produtosCarrinho.quantidade = quantidade;
            produtosCarrinho.totalValor = totalValor;

            localStorage.setItem("produtosCarrinho", JSON.stringify(produtosCarrinho))

            swal({
                    title: 'Salvo!',
                    text: 'Produto adicionado!',
                    type: 'success',
                    showConfirmButton: false
                },
                setTimeout(function () {
                    window.location = "/produto/page";
                }, 1500));

        } else {
            swal('Erro!', 'Informe a quantidade!', 'warning');
        }
    }
);

$('#quantidade').on('input', function (e) {
    // impedir informar quantidade negativa
    if (Number($('#quantidade').val() <= 0)) {
        $('#quantidade').val(0);
    }
});