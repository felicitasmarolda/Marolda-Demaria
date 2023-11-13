package linea;

public class Play {

  public static void main( String[] args) throws Exception {

    System.out.println( "Dimensiones:");

    FourInLine game = new FourInLine( prompt1( "Base? " ), prompt1( "Altura? " ), prompt2( "Estrategia? " ) );

    

    System.out.println( game.show() );

    

    while ( !game.finished() ) {

      game.playRedAt( prompt1( "Rojas? " ) );

      System.out.println( game.show() );

      

      if ( !game.finished() ) {

        game.playBlueAt( prompt1( "Azules? " ) );

        System.out.println( game.show() );
        
        if ( game.finished() ) {

        	System.out.println("Blue wins");
        	
        }

      }
      
      else {
    	  
    	  System.out.println("Red wins");
    	  
      }

    }
    


    

  }



  private static int prompt1( String message ) {

    System.out.print( message );

    return Integer.parseInt( System.console().readLine() );

  }
  
  private static char prompt2( String message ) {

	    System.out.print( message );

	    return ( System.console().readLine() ).toCharArray()[0];

	  }

}