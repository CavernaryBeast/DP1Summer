
package forms;

public class GenerateBuzzWords {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final String voidwordsEnglishList = "a,able,about,across,after,all,almost,also,am,among,an,and,any,are,as,at,be,because,been,but,by,can,cannot,could,dear,did,do,does,either,else,ever,every,for,from,get,got,had,has,have,he,her,hers,him,his,how,however,i,if,in,into,is,it,its,just,least,let,like,likely,may,me,might,most,must,my,neither,no,nor,not,of,off,often,on,only,or,other,our,own,rather,said,say,says,she,should,since,so,some,than,that,the,their,them,then,there,these,they,this,tis,to,too,twas,us,wants,was,we,were,what,when,where,which,while,who,whom,why,will,with,would,yet,you,your,";
		final String voidwordsSpanishList = "a,ac�,ah�,ajena,ajeno,ajenas,ajenos,al,algo,alg�n,alguna,alguno,algunos,algunas,all�/all�,ambos,ante,antes,aquel,aquella,aquello,aquellas,aquellos,aqu�,arriba,as�,atr�s,aun,aunque,bajo,bastante,bien,cabe,cada,casi,cierto,cierta,ciertos,ciertas,como,con,conmigo,conseguimos,conseguir,consigo,consigue,consiguen,consigues,contigo,contra,cual,cuales,cualquier,cualquiera,cualquieras,cuan,cuando,cuanto,cuanta,cuantos,cuantas,de,dejar,del,dem�s,demasiada,demasiado,demasiadas,demasiados,dentro,desde,"
			+ "donde,dos,el,�l,ella,ello,ellas,ellos,emple�is,emplean,emplear,empleas,empleo,en,encima,entonces,entre,era,eras,eramos,eran,eres,es,esa,ese,eso,esas,esos,esta,estas,estaba,estado,est�is,estamos,est�n,estar,este,esto,estos,estoy,etc,fin,fue,fueron,fui,fuimos,gueno,ha,hace ,haces,hac�is,hacemos,hacen,hacer,hacia,hago,hasta,incluso,intenta,intentas,intent�is,intentamos,intentan,intentar,intento,ir,jam�s,junto,juntos,la,lo,las,los,largo,m�s,me,menos,mi,mis,m�a,m�as,mientras,m�o,m�os,misma,mismo,mismas,mismos,modo,mucha,muchas,"
			+ "much�sima,much�simo,much�simas,much�simos,mucho,muchos,muy,nada,ni,ning�n,ninguna,ninguno,ningunas,ningunos,no,nos,nosotras,nosotros,nuestra,nuestro,nuestras,nuestros,nunca,os,otra,otro,otras,otros,para,parecer,pero,poca,poco,pocas,pocos,pod�is,podemos,poder,podr�a,podr�as,podr�ais,podr�amos,podr�an,por,por qu�,porque,primero,puede,pueden,puedo,pues,que,qu�,querer,qui�n,quienes,quienesquiera,quienquiera,quiz�,quiz�s,sabe,sabes,saben,sab�is,sabemos,saber,se,seg�n,ser,si,s�,siempre,siendo,sin,sino,so,sobre,sois,solamente,"
			+ "solo,s�lo,somos,soy,sr,sra,sres,sta,su,sus,suya,suyo,suyas,suyos,tal,tales,tambi�n,tampoco,tan,tanta,tanto,tantas,tantos,te,ten�is,tenemos,tener,tengo,ti,tiempo,tiene,tienen,toda,todo,todas,todos,tomar,trabaja,trabajo,trabaj�is,trabajamos,trabajan,trabajar,trabajas,tras,t�,tu,tus,tuya,tuyo,tuyas,tuyos,�ltimo,ultimo,un,una,unos,unas,usa,usas,us�is,usamos,usan,usar,uso,usted,ustedes,va,van,vais,valor,vamos,varias,varios,vaya,verdadera,vosotras,vosotros,voy,vuestra,vuestro,vuestras,vuestros,y,ya,yo";

		final String wordList = voidwordsEnglishList + voidwordsSpanishList;

		final String[] wordListS = wordList.split(",");
		final char ch = '"';
		for (final String s : wordListS)
			System.out.println("<value>" + s + "</value>");

	}
}
