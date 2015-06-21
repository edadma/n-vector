package ca.hyperreal.nvector


object Main extends App
{
	val montreal = Geo(45.5017, -73.5673)
	val nw100km = distant( montreal, NorthWest, 100 )
	
	println( montreal )
	println( nw100km )
	println( distance(montreal, nw100km) formatted "%.2f" )
}