var arrayProdutos = {

};

function saveJsonCompra(urlDestino) {
    var compra = {
        id: ($("#id").val() != '' ? $("#id").val() : null),
        descricao: $("#descricao").val(),
        data_compra: formatDate($("#data_compra").val()),
        usuario: {id : $("#usuario").val()},
        fornecedor: {id: $("#fornecedor").val()},
        compraProduto: [arrayProdutos]
    }

    console.log(JSON.stringify(compra));

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

function calcularPreco(){

    if ($("#this").val() != ""){

        total = $("#this").val() * $("#valorProduto").val()

        $("#preco").val($("#this").val());
    }
}

function mostrarQuantidade(){
    $("#divQuantidade").hide();

    if ($("#produto").val() != "") {
        $("#divQuantidade").show();
        $("#valorProduto").val();
    }
}

function buscarProduto(idProduto){
    $('')
}

function addCompraProduto() {

    if ($("produto").val() == ''){
        sweetAlert('Selecione um produto!');
    }

    var compraProduto = {
        produto: {id:  $("codigoProduto").val()},
        // pegar no texto hidden
        valor: $("produto").val().get(),
        quantidade: $("quantidade").val(),
    };

    //array que vai manter todos os produtos selecionados
    arrayProdutos.push(compraProduto);
}

function editCompra(url) {
    $.get(url, function (entity, status) {
        $('#id').val(entity.id);
        $('#descricao').val(entity.descricao);
        $('#data_compra').val(formatDate(entity.data_compra));
        $('#usuario').val(entity.usuario);
        $('#fornecedor').val(entity.fornecedor);
    });
    $('#modal-form').modal();
}

function formatDate(inputFormat){
    function pad(s){
        return (s < 10) ? '0' + s : s;
    }
    var d = new Date(inputFormat);
    return [ d.getFullYear(), pad(d.getMonth()+1), pad(d.getDate()) ].join('-');
}