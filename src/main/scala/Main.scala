package ca.hyperreal.nvector


object Main extends App
{
	val montreal = Geo(45.536482, -73.592702)
	val phoenix = Geo(33.4500, -112.0667)
	
	val m = fromGeo( montreal )
	val axis = normalize( m cross North )
	val d100 = angle(100)
	val n100 = rotate( m, axis, d100 )
	val sideways = normalize( axis cross m )
	val e100 = rotate( m, sideways, d100 )
	
	println( distance(montreal, phoenix) )
	println( montreal )
	println( toGeo(e100), distance(m, e100) )
}
