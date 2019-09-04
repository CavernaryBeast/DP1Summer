
package forms;

public class GenerateBuzzWords {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final String voidwordsEnglishList = "a,able,about,across,after,all,almost,also,am,among,an,and,any,are,as,at,be,because,been,but,by,can,cannot,could,dear,did,do,does,either,else,ever,every,for,from,get,got,had,has,have,he,her,hers,him,his,how,however,i,if,in,into,is,it,its,just,least,let,like,likely,may,me,might,most,must,my,neither,no,nor,not,of,off,often,on,only,or,other,our,own,rather,said,say,says,she,should,since,so,some,than,that,the,their,them,then,there,these,they,this,tis,to,too,twas,us,wants,was,we,were,what,when,where,which,while,who,whom,why,will,with,would,yet,you,your,";
		final String voidwordsSpanishList = "a,acá,ahí,ajena,ajeno,ajenas,ajenos,al,algo,algún,alguna,alguno,algunos,algunas,allá/allí,ambos,ante,antes,aquel,aquella,aquello,aquellas,aquellos,aquí,arriba,así,atrás,aun,aunque,bajo,bastante,bien,cabe,cada,casi,cierto,cierta,ciertos,ciertas,como,con,conmigo,conseguimos,conseguir,consigo,consigue,consiguen,consigues,contigo,contra,cual,cuales,cualquier,cualquiera,cualquieras,cuan,cuando,cuanto,cuanta,cuantos,cuantas,de,dejar,del,demás,demasiada,demasiado,demasiadas,demasiados,dentro,desde,"
			+ "donde,dos,el,él,ella,ello,ellas,ellos,empleáis,emplean,emplear,empleas,empleo,en,encima,entonces,entre,era,eras,eramos,eran,eres,es,esa,ese,eso,esas,esos,esta,estas,estaba,estado,estáis,estamos,están,estar,este,esto,estos,estoy,etc,fin,fue,fueron,fui,fuimos,gueno,ha,hace ,haces,hacéis,hacemos,hacen,hacer,hacia,hago,hasta,incluso,intenta,intentas,intentáis,intentamos,intentan,intentar,intento,ir,jamás,junto,juntos,la,lo,las,los,largo,más,me,menos,mi,mis,mía,mías,mientras,mío,míos,misma,mismo,mismas,mismos,modo,mucha,muchas,"
			+ "muchísima,muchísimo,muchísimas,muchísimos,mucho,muchos,muy,nada,ni,ningún,ninguna,ninguno,ningunas,ningunos,no,nos,nosotras,nosotros,nuestra,nuestro,nuestras,nuestros,nunca,os,otra,otro,otras,otros,para,parecer,pero,poca,poco,pocas,pocos,podéis,podemos,poder,podría,podrías,podríais,podríamos,podrían,por,por qué,porque,primero,puede,pueden,puedo,pues,que,qué,querer,quién,quienes,quienesquiera,quienquiera,quizá,quizás,sabe,sabes,saben,sabéis,sabemos,saber,se,según,ser,si,sí,siempre,siendo,sin,sino,so,sobre,sois,solamente,"
			+ "solo,sólo,somos,soy,sr,sra,sres,sta,su,sus,suya,suyo,suyas,suyos,tal,tales,también,tampoco,tan,tanta,tanto,tantas,tantos,te,tenéis,tenemos,tener,tengo,ti,tiempo,tiene,tienen,toda,todo,todas,todos,tomar,trabaja,trabajo,trabajáis,trabajamos,trabajan,trabajar,trabajas,tras,tú,tu,tus,tuya,tuyo,tuyas,tuyos,último,ultimo,un,una,unos,unas,usa,usas,usáis,usamos,usan,usar,uso,usted,ustedes,va,van,vais,valor,vamos,varias,varios,vaya,verdadera,vosotras,vosotros,voy,vuestra,vuestro,vuestras,vuestros,y,ya,yo";

		final String wordList = voidwordsEnglishList + voidwordsSpanishList;

		final String[] wordListS = wordList.split(",");
		final char ch = '"';
		for (final String s : wordListS)
			System.out.println("<value>" + s + "</value>");

	}
}
