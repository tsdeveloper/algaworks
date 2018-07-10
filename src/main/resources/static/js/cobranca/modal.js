
$('.datepicker').datepicker({
    format: 'mm/dd/yyyy',
    startDate: '-3d'
});

$('#modal-tituloconfirmacao-delete').on('shown.bs.modal', function (e) {

    var button = $(e.relatedTarget);

    var codigoTitulo = button.data('codigo');
    var descricao = button.data('descricao');

    var modal = $(this),
        form = modal.find('form'),
        url_base = form.data('url-base');

   console.log('url_base:',url_base);

   if(!url_base.endsWith('/'))
       url_base += '/';

    form.attr('action', url_base + codigoTitulo);


    console.log('action:',form.attr('action'));

    modal.find('.modal-body p').html('Tem certeza que deseja realmente exlcuír o título <strong>código: ' + codigoTitulo +  ' Nome: '+ descricao +' </strong>?');
});

