/*
 * Copyright 2007-2010 WorldWide Conferencing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.liftweb {
package flot_demo {
package web {
package snippet {

import scala.xml.NodeSeq

import net.liftweb.common._
import net.liftweb.util._
import Helpers._
import net.liftweb.http.js.JsCmds._
import net.liftmodules.widgets.flot._

/*
 *
 */

class Visitors {

  def render (xhtml: NodeSeq) = {

    /*
    */

    def graph () = {
      val _data =  (1196463600000.0, 0.0) ::  (1196550000000.0, 0.0) ::  (1196636400000.0, 0.0) ::
                   (1196722800000.0, 77.0) ::  (1196809200000.0, 3636.0) ::  (1196895600000.0, 3575.0) ::
                 //  (1196982000000.0, 2736.0) ::  (1197068400000.0, 1086.0) ::  (1197154800000.0, 676.0) ::
                 //  (1197241200000.0, 1205.0) ::  (1197327600000.0, 906.0) ::  (1197414000000.0, 710.0) ::
                  (1197500400000.0, 639.0) ::  (1197586800000.0, 540.0) ::  (1197673200000.0, 435.0) ::
                 //  (1197759600000.0, 301.0) ::  (1197846000000.0, 575.0) ::  (1197932400000.0, 481.0) ::
                 //  (1198018800000.0, 591.0) ::  (1198105200000.0, 608.0) ::  (1198191600000.0, 459.0) ::
                 //  (1198278000000.0, 234.0) ::  (1198364400000.0, 1352.0) ::  (1198450800000.0, 686.0) ::
                  (1198537200000.0, 279.0) ::  (1198623600000.0, 449.0) ::  (1198710000000.0, 468.0) ::
                 //  (1198796400000.0, 392.0) ::  (1198882800000.0, 282.0) ::  (1198969200000.0, 208.0) ::
                 //  (1199055600000.0, 229.0) ::  (1199142000000.0, 177.0) ::  (1199228400000.0, 374.0) ::
                 //  (1199314800000.0, 436.0) ::  (1199401200000.0, 404.0) ::  (1199487600000.0, 253.0) ::
                  (1199574000000.0, 218.0) ::  (1199660400000.0, 476.0) ::  (1199746800000.0, 462.0) ::
                 //  (1199833200000.0, 448.0) ::  (1199919600000.0, 442.0) ::  (1200006000000.0, 403.0) ::
                 //  (1200092400000.0, 204.0) ::  (1200178800000.0, 194.0) ::  (1200265200000.0, 327.0) ::
                 //  (1200351600000.0, 374.0) ::  (1200438000000.0, 507.0) ::  (1200524400000.0, 546.0) ::
                  (1200610800000.0, 482.0) ::  (1200697200000.0, 283.0) ::  (1200783600000.0, 221.0) ::
                 //  (1200870000000.0, 483.0) ::  (1200956400000.0, 523.0) ::  (1201042800000.0, 528.0) ::
                 //  (1201129200000.0, 483.0) ::  (1201215600000.0, 452.0) ::  (1201302000000.0, 270.0) ::
                 //  (1201388400000.0, 222.0) ::  (1201474800000.0, 439.0) ::  (1201561200000.0, 559.0) ::
                  (1201647600000.0, 521.0) ::  (1201734000000.0, 477.0) ::  (1201820400000.0, 442.0) ::
                 //  (1201906800000.0, 252.0) ::  (1201993200000.0, 236.0) ::  (1202079600000.0, 525.0) ::
                 //  (1202166000000.0, 477.0) ::  (1202252400000.0, 386.0) ::  (1202338800000.0, 409.0) ::
                 //  (1202425200000.0, 408.0) ::  (1202511600000.0, 237.0) ::  (1202598000000.0, 193.0) ::
                  (1202684400000.0, 357.0) ::  (1202770800000.0, 414.0) ::  (1202857200000.0, 393.0) ::
                 //  (1202943600000.0, 353.0) ::  (1203030000000.0, 364.0) ::  (1203116400000.0, 215.0) ::
                 //  (1203202800000.0, 214.0) ::  (1203289200000.0, 356.0) ::  (1203375600000.0, 399.0) ::
                 //  (1203462000000.0, 334.0) ::  (1203548400000.0, 348.0) ::  (1203634800000.0, 243.0) ::
                  (1203721200000.0, 126.0) ::  (1203807600000.0, 157.0) ::  (1203894000000.0, 288.0) :: Nil

      val s = new FlotSerie () {
        override val data = _data
      }

      val options = new FlotOptions () {
        override val xaxis = Full (new FlotAxisOptions () {
          override val mode = Full ("time")
        })
        override val modeSelection = Full ("x")
        override val grid = Full (new FlotGridOptions () {
          override val coloredAreas = Full ("weekendAreas")
        })

      }

      val optionsOverview = new FlotOptions () {
        override val lines = Full(new FlotLinesOptions () {
          override val show = Full (true)
          override val lineWidth = Full (1)
        })
        override val shadowSize = Full (0)
        override val xaxis = Full (new FlotAxisOptions () {
          // override val ticks = 3.0 :: Nil
          override val mode = Full ("time")
        })
        override val yaxis = Full (new FlotAxisOptions () {
          // override val ticks = 3.0 :: Nil
          override val min = Full (0.0)
          override val max = Full (4000.0)
        })
        override val modeSelection = Full ("x")
      }

      val overview = new FlotOverview ("ph_overview", optionsOverview)

      Flot.render ("ph_graph", s :: Nil, options, Flot.script(xhtml), overview)
    }

    //

    bind ("flot", xhtml, "graph" -> graph)
  }
}
}
}
}
}
