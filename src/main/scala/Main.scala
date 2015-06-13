package ca.hyperreal.nvector


object Main extends App
{
	val montreal = Geo(45.536482, -73.592702)
	val phoenix = Geo(33.4500, -112.0667)
	
	println( distance(montreal, phoenix) )
}
