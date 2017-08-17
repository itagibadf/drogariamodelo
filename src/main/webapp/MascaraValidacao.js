/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function validarCPF( cpf ){
	var filtro = /^\d{3}.\d{3}.\d{3}-\d{2}$/i;

	if(!filtro.test(cpf))
	{
		window.alert("CPF inválido. Tente novamente.");
		return false;
	}

	cpf = remove(cpf, ".");
	cpf = remove(cpf, "-");

	if(cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" ||
		cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" ||
		cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" ||
		cpf == "88888888888" || cpf == "99999999999")
	{
		window.alert("CPF inválido. Tente novamente.");
		return false;
   }

	soma = 0;
	for(i = 0; i < 9; i++)
	{
		soma += parseInt(cpf.charAt(i)) * (10 - i);
	}

	resto = 11 - (soma % 11);
	if(resto == 10 || resto == 11)
	{
		resto = 0;
	}
	if(resto != parseInt(cpf.charAt(9))){
		window.alert("CPF inválido. Tente novamente.");
		return false;
	}

	soma = 0;
	for(i = 0; i < 10; i ++)
	{
		soma += parseInt(cpf.charAt(i)) * (11 - i);
	}
	resto = 11 - (soma % 11);
	if(resto == 10 || resto == 11)
	{
		resto = 0;
	}

	if(resto != parseInt(cpf.charAt(10))){
		window.alert("CPF inválido. Tente novamente.");
		return false;
	}

	return true;
 }

function MontaCidadesUsuario(cidadeUsuario,documento){

    var comboCidades = documento.getElementById("cidade");

    var opt0 = documento.createElement("option");
    opt0.value = "";
    opt0.text = "Selecione sua Cidade";
    if (cidadeUsuario == opt0.value) {
        opt3.selected = "true";
    }
    comboCidades.add(opt0, comboCidades.options[0]);

    var opt1 = documento.createElement("option");
    opt1.value = "Águas Claras";
    opt1.text = "Águas Claras";
    if (cidadeUsuario == opt1.value) {
        opt3.selected = "true";
    }
    comboCidades.add(opt1, comboCidades.options[1]);

    var opt2 = documento.createElement("option");
    opt2.value = "Ceilândia";
    opt2.text = "Ceilândia";
    if (cidadeUsuario == opt2.value) {
        opt3.selected = "true";
    }
    comboCidades.add(opt2, comboCidades.options[2]);

    var opt3 = documento.createElement("option");
    opt3.value = "Guará";
    opt3.text = "Guará";
    if (cidadeUsuario == opt3.value) {
        opt3.selected = "true";
    }
    comboCidades.add(opt3, comboCidades.options[3]);
}

function remove(str, sub) {
	i = str.indexOf(sub);
	r = "";
	if (i == -1) return str;
	{
		r += str.substring(0,i) + remove(str.substring(i + sub.length), sub);
	}

	return r;
}

/**
   * MASCARA ( mascara(o,f) e execmascara() ) 
   * elcio.com.br
   */
function mascara(o,f){
	v_obj=o
	v_fun=f
	setTimeout("execmascara()",1)
}

function execmascara(){
	v_obj.value=v_fun(v_obj.value)
}

function cpf_mask(v){
	v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito
	v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o terceiro e o quarto dígitos
	v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o setimo e o oitava dígitos
	v=v.replace(/(\d{3})(\d)/,"$1-$2")   //Coloca ponto entre o decimoprimeiro e o decimosegundo dígitos
	return v
}
//valida numero inteiro com mascara
function mascaraInteiro(){
        if (event.keyCode < 48 || event.keyCode > 57){
                event.returnValue = false;
                return false;
        }
        return true;
}
//adiciona mascara de cep

//Código para a formação da sáida do cep
function formatar(mascara, documento){
//   Formata os caracteres
   var i = documento.value.length;
   var saida = mascara.substring(0,1);
  var texto = mascara.substring(i)
  mascara=mascara.replace(/\.|\-/g, '');

  

 if (texto.substring(0,1) != saida){
          documento.value += texto.substring(0,1);
 //Formata os campos do CEP
exp = /\d{2}\.\d{3}\-\d{3}/
}
}
     //valida CEP
function ValidaCep(cep){
//Verifica se os dígitos estão de acordo com a formatação da mascara
 if(!exp.test(cep.value))
     alert("Cep Inválido: Verifique os dígitos");
 }
                 
                 
          




