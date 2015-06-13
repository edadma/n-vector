package ca.hyperreal.nvector

import org.scalatest._
import prop.PropertyChecks


class Tests extends FreeSpec with PropertyChecks with Matchers
{
	val montreal = Geo(45.5017, -73.5673)
	val m = fromGeo( montreal )
	val axis = normalize( m cross North )
	val d100 = angle(100)
	val n100 = rotate( m, axis, d100 )
	
	"distances" in
	{
		distance( m, n100 ) shouldBe 100.0 +- .0001
	}
}