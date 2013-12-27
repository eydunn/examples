/*
 * Copyright 2010 WorldWide Conferencing, LLC
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
 
package fbc.example {
package snippet {

import fbc.example.model._

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

import net.liftmodules.ext_api.facebook.{FacebookRestApi,FacebookClient, FacebookConnect, FacebookSession}

import net.liftweb.http.js.JsCmds
import net.liftweb.http.js.JE
import net.liftweb.http.js._
import net.liftweb.http.SHtml
import net.liftweb.http.S

import JsCmds._
import JE._

import Helpers._

class ConnectSnippet {
  
  def initfb:NodeSeq = {
    Script(Call("FB.init", Str(FacebookRestApi.apiKey), Str("/xd_receiver.html")))
  }
  
  def fbloginLink:NodeSeq = SHtml.a(Text("Login With Fb"),loginCmd)
  
  def redirectRoot = RedirectTo("/")
  
  def fbconnectLink(in:NodeSeq):NodeSeq = {
    val user = User.currentUser.open_! //must be logged in
    if (user.fbid.is == 0) 
      SHtml.a(Text("Link your account with fb"),connectCmd) 
    else
      bind("f", in,
        AttrBindParam("uid", Text(user.fbid.is.toString), "uid"),
        "unlink" -> SHtml.a(() => {
            user.fbid(0).save
            Alert("Unlinked") & redirectRoot
          },Text("Unlink from facebook"))
      )
}
  
  
  
  def requireSession(f: FacebookSession => JsCmd):JsCmd = {
    val ajaxLogin = SHtml.ajaxInvoke(() => {
      FacebookConnect.session match {
        case Full(session) => f(session)
        case _ => Alert("Failed to create Facebook session")
      }
    })._2
    
    Call("FB.Connect.requireSession", AnonFunc(ajaxLogin))
  }
  
  def loginCmd:JsCmd = requireSession { session =>    
      User.findByFbId(session.uid) match {
        case Full(user) =>
          User.logUserIn(user)
          S.notice("You're now logged in")
          redirectRoot
        case _ =>
          S.error("You need to register first")
          RedirectTo("/user_mgt/sign_up")
      }
  }
  
  def connectCmd:JsCmd = requireSession { session =>
    val user = User.currentUser.open_!
    user.fbid(session.uid.toLong).validate match {
      case Nil => 
        user.save
        S.notice("Linked to facebook connect")
      case xs => S.error(xs)
    }
    redirectRoot
  }
}

}
}

