var pedidosList = [];

var trChecked = $('table').find('input:checked'),
                inputHidden = trChecked.closest('tr').find('table > tbody tr:last').find('input:hidden');

if(!inputHidden.length){
    inputHidden = trChecked.closest('tr').next().find('table > tbody tr').find('input:hidden');
}

console.log('tr:', trChecked);
console.log('inputHidden:', inputHidden);

$(inputHidden).each(function (i, e){
    console.log('i:', i);
    console.log('e:', $(e).val());
    pedidosList.push({
        NumComp: $(e).val()
    })
});

console.log('pedidosList:', pedidosList);