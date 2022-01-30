package com.example.knowledgehunt.presentation.screens.about

import android.widget.TextView
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.navigation.NavHostController
import com.example.knowledgehunt.domain.models.Screens


@Composable
fun About(openDrawer: () -> Unit, navController: NavHostController) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()).padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val source = "<div class=\"panel-heading\">\n" +
                    "<div>\n" +
                    "<h1 id=\"bf34\" class=\"fx dl fy bb fz ga gb gc gd ge gf gg gh gi gj gk gl gm gn go gp gq gr gs gt gu gv\" data-selectable-paragraph=\"\">The future of apps:<br />Declarative UIs with Kotlin MultiPlatform (D-KMP) &mdash; Part 1/3</h1>\n" +
                    "<div class=\"cx\">\n" +
                    "<div class=\"n ck gw gx gy\">\n" +
                    "<div class=\"o n\">\n" +
                    "<div><a href=\"https://danielebaroncelli.medium.com/?source=post_page-----c0e1530a5343-----------------------------------\" rel=\"noopener follow\"><img class=\"s gz ha hb\" src=\"https://miro.medium.com/fit/c/56/56/1*6VA7kjBjltDxDYZIjBQqtw.jpeg\" alt=\"Daniele Baroncelli\" width=\"28\" height=\"28\" /></a></div>\n" +
                    "<div class=\"hc w n cu\">\n" +
                    "<div class=\"n\">\n" +
                    "<div>\n" +
                    "<div>\n" +
                    "<div class=\"bw\">\n" +
                    "<p class=\"bb b bc bd be\">Daniele Baroncelli</p>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<p class=\"bb b bc bd bz\"><span>Nov 18, 2020</span><span class=\"he\">&middot;</span>7 min read</p>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<div class=\"n ct hf hg hh hi hj hk hl hm\">&nbsp;</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<p id=\"1011\" class=\"hq hr fy hs b ht hu hv hw hx hy hz ia ib ic id ie if ig ih ii ij dn gv\" data-selectable-paragraph=\"\"><em class=\"ik\">A 3-part article explaining the new D-KMP architecture, based on DeclarativeUIs, Kotlin MultiPlatform and MVI pattern.</em></p>\n" +
                    "<p id=\"c598\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">-&nbsp;<span class=\"hs fz\">Part 1: The D-KMP architecture and the Declarative UIs</span><br />- Part 2:&nbsp;<a class=\"dy iq\" href=\"https://medium.com/@danielebaroncelli/the-future-of-apps-declarative-uis-with-kotlin-multiplatform-d-kmp-part-3-3-1bbadaf19aef\" rel=\"noopener\">Kotlin MultiPlatform and the MVI pattern</a><br />- Part 3:&nbsp;<a class=\"dy iq\" href=\"https://medium.com/@danielebaroncelli/the-future-of-apps-declarative-uis-with-kotlin-multiplatform-d-kmp-part-3-3-959a2628526d\" rel=\"noopener\">D-KMP layers and Team organization</a></p>\n" +
                    "<p id=\"b6a5\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\"><em class=\"ik\">Latest Update: May 18, 2021</em></p>\n" +
                    "<div class=\"iy iz ap ja w jb\">\n" +
                    "<div class=\"fg fh ir\"><img class=\"w jc jd\" src=\"https://miro.medium.com/max/1400/1*qOiRVZEPsR5FrURz73XuBA.png\" alt=\"\" width=\"700\" height=\"482\" /></div>\n" +
                    "</div>\n" +
                    "<div class=\"iy iz ap ja w jb\">\n" +
                    "<div class=\"fg fh je\"><img class=\"w jc jd\" src=\"https://miro.medium.com/max/1400/1*YXeYR0SiDXOYtHbY70j-hA.png\" alt=\"\" width=\"700\" height=\"372\" /></div>\n" +
                    "</div>\n" +
                    "<p id=\"5920\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">Year 2020 has not only been the year of the pandemic, but also the year which set the division between&nbsp;<span class=\"hs fz\">The Past</span>&nbsp;and&nbsp;<span class=\"hs fz\">The Future</span>&nbsp;in apps development.</p>\n" +
                    "<p id=\"7c1a\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\"><em class=\"ik\">The Future</em>&nbsp;will already&nbsp;<span id=\"rmm\">s</span>tart in 2021 and it will be about&nbsp;<span class=\"hs fz\">DeclarativeUIs</span>&nbsp;and&nbsp;<span class=\"hs fz\">MultiPlatform</span>, changing forever the way apps are architected and implemented. It will become normal to build apps for all platforms, which share 85% of the code and have the latest native UI/UX.&nbsp;Development productivity will massively improve, and apps quality too!</p>\n" +
                    "<p id=\"f43d\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">In this article we&rsquo;ll go through the main concepts, also how everything is coming together nicely.</p>\n" +
                    "<div class=\"iy iz ap ja w jb\">\n" +
                    "<div class=\"fg fh jf\">\n" +
                    "<div class=\"jk s ap jl\">\n" +
                    "<div class=\"jm jn s\">\n" +
                    "<div class=\"ep jg ef es eo ex w jh ji jj\"><img class=\"ef es eo ex w jo jp af wm\" src=\"https://miro.medium.com/max/60/1*9EOoAhmiabR4S4Xqwxxb7g.png?q=20\" alt=\"\" width=\"700\" height=\"465\" /></div>\n" +
                    "<img class=\"oe tg ef es eo ex w c\" src=\"https://miro.medium.com/max/1400/1*9EOoAhmiabR4S4Xqwxxb7g.png\" alt=\"\" width=\"700\" height=\"465\" /></div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<div class=\"iy iz ap ja w jb\">\n" +
                    "<div class=\"fg fh jr\">\n" +
                    "<div class=\"jk s ap jl\">\n" +
                    "<div class=\"js jn s\">\n" +
                    "<div class=\"ep jg ef es eo ex w jh ji jj\"><img class=\"ef es eo ex w jo jp af wm\" src=\"https://miro.medium.com/max/60/1*P2ow0SLwIK0GQF0w5ogTEg.png?q=20\" alt=\"\" width=\"700\" height=\"354\" /></div>\n" +
                    "<img class=\"oe tg ef es eo ex w c\" src=\"https://miro.medium.com/max/1400/1*P2ow0SLwIK0GQF0w5ogTEg.png\" alt=\"\" width=\"700\" height=\"354\" /></div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<h1 id=\"696d\" class=\"jt ju fy bb da jv jw hu jx jy jz hx ka kb kc kd ke kf kg kh ki kj kk kl km kn gv\" data-selectable-paragraph=\"\">The Past</h1>\n" +
                    "<p id=\"9de8\" class=\"hq hr fy hs b ht ko hu hv hw kp hx hy hz kq ia ib ic kr id ie if ks ig ih ij dn gv\" data-selectable-paragraph=\"\">The &ldquo;past&rdquo; is what app development has been until now, where most companies have built separate apps for each platform (<em class=\"ik\">Android</em>,&nbsp;<em class=\"ik\">iOS</em>,&nbsp;<em class=\"ik\">Web</em>), with&nbsp;<span class=\"hs fz\">no shared code</span>&nbsp;on the client-side.</p>\n" +
                    "<p id=\"aec7\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">In order to limit the amount of code being replicated for each platform, most&nbsp;<span class=\"hs fz\">apps tend to be &ldquo;<em class=\"ik\">thin client&rdquo;</em></span>, delegating most business logic and data processing to the only part that is really shared: the webservices.</p>\n" +
                    "<p id=\"368c\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">In this way,&nbsp;<span class=\"hs fz\">webservices tend to be &ldquo;<em class=\"ik\">UI-oriented&rdquo;</em></span>. Architectures are designed in a way that most clicks trigger an API call, which provides the exact information to be displayed on the next view, with very limited client-side logic.</p>\n" +
                    "<p id=\"d5db\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">Any extra client-side logic requires a&nbsp;<span class=\"hs fz\">replication</span>&nbsp;of the same code&nbsp;<span class=\"hs fz\">on each platform</span>, so this is something that is typically avoided, unless it brings a meaningful benefit for the user experience.</p>\n" +
                    "<p id=\"b0d3\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">In terms of client-side&nbsp;<span class=\"hs fz\">shared code</span>, there have been several companies that attempted some strategies in these years, but for the most part it&rsquo;s a&nbsp;<span class=\"hs fz\">history of failures</span>, with many reverting back to native-only/platform-specific development. There are famous examples such as&nbsp;<em class=\"ik\">DropBox</em>&nbsp;(<a class=\"dy iq\" href=\"https://dropbox.tech/mobile/the-not-so-hidden-cost-of-sharing-code-between-ios-and-android\" rel=\"noopener ugc nofollow\" target=\"_blank\">moving away from shared C++ code</a>) or&nbsp;<em class=\"ik\">AirBnB</em>&nbsp;(<a class=\"dy iq\" href=\"https://medium.com/airbnb-engineering/sunsetting-react-native-1868ba28e30a\" rel=\"noopener\">moving away from ReactNative</a>). In other words, there haven&rsquo;t been suitable technologies to allow companies to make a safe long-term investment in shared code.</p>\n" +
                    "<h1 id=\"bea2\" class=\"jt ju fy bb da jv jw hu jx jy jz hx ka kb kc kd ke kf kg kh ki kj kk kl km kn gv\" data-selectable-paragraph=\"\">The Future</h1>\n" +
                    "<p id=\"c449\" class=\"hq hr fy hs b ht ko hu hv hw kp hx hy hz kq ia ib ic kr id ie if ks ig ih ij dn gv\" data-selectable-paragraph=\"\">In this 2020, we are experiencing the rise of two important paradigms, which are happening in parallel:&nbsp;<span class=\"hs fz\"><em class=\"ik\">Declarative UIs</em></span>&nbsp;and&nbsp;<span class=\"hs fz\"><em class=\"ik\">Kotlin MultiPlatform</em></span>. This will bring opportunities never seen before, and it will make &ldquo;<em class=\"ik\">Multi-Platform</em>&rdquo; and client-side&nbsp;<em class=\"ik\">shared code</em>&nbsp;the preferred choice for apps development.</p>\n" +
                    "<p id=\"7552\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">DeclarativeUIs fit perfectly in a multi-platform architecture, because they are&nbsp;<span class=\"hs fz\">stateless</span>&nbsp;and can be&nbsp;<span class=\"hs fz\">fully decoupled</span>&nbsp;from the business logic. By combining Declarative UIs with Kotlin MultiPlatform, we can safely architect apps with a huge amount of client-side&nbsp;<span class=\"hs fz\">shared code</span>&nbsp;(up to&nbsp;<span class=\"hs fz\">85%</span>), performing as&nbsp;<span class=\"hs fz\">perfectly-native</span>&nbsp;<span class=\"hs fz\">on each platform</span>. At the same time we can have the latest&nbsp;<span class=\"hs fz\">native UI/UX</span>&nbsp;on each platform.</p>\n" +
                    "<p id=\"44b2\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">Apps can now be&nbsp;<span class=\"hs fz\">&ldquo;rich client&rdquo;</span>, as&nbsp;<span class=\"hs fz\">client-side logic is inexpensive</span>, due to the fact that it doesn&rsquo;t need to be replicated for each platform anymore. Apps can now be incredibly smart and bring the most advanced optimization in terms of&nbsp;<span class=\"hs fz\">user experience</span>, decreasing the number of situations where the user has to wait after a click.</p>\n" +
                    "<p id=\"b80b\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\"><span class=\"hs fz\">Webservices</span>&nbsp;can now be fully&nbsp;<span class=\"hs fz\">&ldquo;UI-agnostic&rdquo;&nbsp;</span>and focus on providing the data in the most normalized way, removing any redundancy, as all data processing and formatting can be done at client level. This can also greatly&nbsp;<span class=\"hs fz\">improve data consumption</span>.</p>\n" +
                    "<p id=\"f2f8\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">But let&rsquo;s go on with order. Let&rsquo;s start by defining what are the pillars for the upcoming era of apps development.</p>\n" +
                    "<h1 id=\"536c\" class=\"jt ju fy bb da jv jw hu jx jy jz hx ka kb kc kd ke kf kg kh ki kj kk kl km kn gv\" data-selectable-paragraph=\"\">The 3 pillars for the future of apps</h1>\n" +
                    "<ul>\n" +
                    "<li id=\"b938\" class=\"hq hr fy hs b ht ko hu hv hw kp hx hy hz kq ia ib ic kr id ie if ks ig ih ij kt ku kv gv\" data-selectable-paragraph=\"\"><span class=\"hs fz\">Declarative UIs</span>&nbsp;(<em class=\"ik\">JetpackCompose</em>&nbsp;on Android,&nbsp;<em class=\"ik\">SwiftUI</em>&nbsp;on iOS)</li>\n" +
                    "<li id=\"5215\" class=\"hq hr fy hs b ht kw hu hv hw kx hx hy hz ky ia ib ic kz id ie if la ig ih ij kt ku kv gv\" data-selectable-paragraph=\"\"><span class=\"hs fz\">KMP</span>&nbsp;(Kotlin MultiPlatform)</li>\n" +
                    "<li id=\"e5b9\" class=\"hq hr fy hs b ht kw hu hv hw kx hx hy hz ky ia ib ic kz id ie if la ig ih ij kt ku kv gv\" data-selectable-paragraph=\"\"><span class=\"hs fz\">MVI pattern</span>&nbsp;(Model-View-Intent)</li>\n" +
                    "</ul>\n" +
                    "<p id=\"75d2\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">We call this architecture&nbsp;<span class=\"hs fz\">D-KMP</span>, standing for&nbsp;<em class=\"ik\">DeclarativeUIs</em>&nbsp;with&nbsp;<em class=\"ik\">Kotlin MultiPlatform</em>. The&nbsp;<em class=\"ik\">MVI pattern</em>&nbsp;is what makes the two work best together.</p>\n" +
                    "<h1 id=\"f12a\" class=\"jt ju fy bb da jv jw hu jx jy jz hx ka kb kc kd ke kf kg kh ki kj kk kl km kn gv\" data-selectable-paragraph=\"\">The D-KMP architecture</h1>\n" +
                    "<div class=\"iy iz ap ja w jb\">\n" +
                    "<div class=\"fg fh lb\">\n" +
                    "<div class=\"jk s ap jl\">\n" +
                    "<div class=\"lo jn s\">\n" +
                    "<div class=\"ep jg ef es eo ex w jh ji jj\"><img class=\"ef es eo ex w jo jp af wm\" src=\"https://miro.medium.com/max/54/1*bPZlgBPj5P7CZu22TumPvw.png?q=20\" alt=\"\" width=\"500\" height=\"561\" /></div>\n" +
                    "<img class=\"oe tg ef es eo ex w c\" src=\"https://miro.medium.com/max/1000/1*bPZlgBPj5P7CZu22TumPvw.png\" alt=\"\" width=\"500\" height=\"561\" /></div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<p id=\"a933\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">It&rsquo;s important to clarify that the D-KMP architecture we are presenting is aimed at&nbsp;<span class=\"hs fz\">greenfield projects</span>.</p>\n" +
                    "<p id=\"21f8\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">We are not talking about how to gradually introduce&nbsp;<em class=\"ik\">Declarative UIs</em>&nbsp;and&nbsp;<em class=\"ik\">Kotlin MultiPlatform</em>&nbsp;to an existing project.</p>\n" +
                    "<p id=\"fd3d\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">Our goal is a&nbsp;<span class=\"hs fz\">clean</span>,&nbsp;<span class=\"hs fz\">solid</span>, and&nbsp;<span class=\"hs fz\">future-proof</span>&nbsp;architecture, which doesn&rsquo;t make compromises with the past, and it builds on the most innovative technologies and paradigms.</p>\n" +
                    "<p id=\"af61\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">It&rsquo;s also important to highlight that&nbsp;<span class=\"hs fz\">D-KMP is not a library</span>, but an architecture, fully relying on the official frameworks.</p>\n" +
                    "<p id=\"7272\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">Let&rsquo;s now go in more detail about each of the 3 pillars of the architecture, starting with the DeclarativeUIs.</p>\n" +
                    "<h1 id=\"4c64\" class=\"jt ju fy bb da jv jw hu jx jy jz hx ka kb kc kd ke kf kg kh ki kj kk kl km kn gv\" data-selectable-paragraph=\"\"><span class=\"ce\">Declarative UIs have landed on Android and iOS!</span></h1>\n" +
                    "<p id=\"8405\" class=\"hq hr fy hs b ht ko hu hv hw kp hx hy hz kq ia ib ic kr id ie if ks ig ih ij dn gv\" data-selectable-paragraph=\"\">After over a decade, we are experiencing the most important&nbsp;<span class=\"hs fz\">revolution</span>&nbsp;for the mobile frameworks. Both&nbsp;<span class=\"hs fz\">Android</span>&nbsp;and&nbsp;<span class=\"hs fz\">iOS</span>&nbsp;have started to ship their&nbsp;<span class=\"hs fz\">new UI toolkits</span>, which are both declarative, taking inspiration from the paradigms of&nbsp;<em class=\"ik\">React</em>&nbsp;and&nbsp;<em class=\"ik\">Flutter</em>. They are going to fully replace the current way of defining views in both operating systems.</p>\n" +
                    "<div class=\"iy iz ap ja w jb\">\n" +
                    "<div class=\"fg fh lp\">\n" +
                    "<div class=\"jk s ap jl\">\n" +
                    "<div class=\"lq jn s\">\n" +
                    "<div class=\"ep jg ef es eo ex w jh ji jj\"><img class=\"ef es eo ex w jo jp af wm\" src=\"https://miro.medium.com/max/60/1*8FqQC_aYHbR8Rx3VMBFhWg.png?q=20\" alt=\"\" width=\"700\" height=\"390\" /></div>\n" +
                    "<img class=\"oe tg ef es eo ex w c\" src=\"https://miro.medium.com/max/1400/1*8FqQC_aYHbR8Rx3VMBFhWg.png\" alt=\"\" width=\"700\" height=\"390\" /></div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<p id=\"d319\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">Google announced&nbsp;<span class=\"hs fz\">JetpackCompose</span>&nbsp;at&nbsp;<a class=\"dy iq\" href=\"https://www.youtube.com/watch?v=VsStyq4Lzxo\" rel=\"noopener ugc nofollow\" target=\"_blank\">Google I/O 2019</a>. It entered&nbsp;<em class=\"ik\">Alpha</em>&nbsp;stage in August 2020. It is expected to reach&nbsp;<em class=\"ik\">Beta</em>&nbsp;in spring 2021, and&nbsp;<em class=\"ik\">version 1.0</em>&nbsp;by the end of 2021.<br /><span class=\"hs fz\">JetpackCompose is supported by any device with Android 5 and later</span>&nbsp;(target API 21). This means that any new JetpackCompose API will be&nbsp;<span class=\"hs fz\">backward compatible</span>, and won&rsquo;t require the new Android version. This is because JetpackCompose operates low level, by drawing directly on the Android view canvas.</p>\n" +
                    "<p id=\"0b57\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">Apple announced&nbsp;<span class=\"hs fz\">SwiftUI</span>&nbsp;at&nbsp;<a class=\"dy iq\" href=\"https://developer.apple.com/videos/play/wwdc2019/204\" rel=\"noopener ugc nofollow\" target=\"_blank\">WWDC 2019</a>, which was already shipped in 2019 with&nbsp;<em class=\"ik\">iOS13</em>. There were further improvements this year with the&nbsp;<em class=\"ik\">iOS14</em>&nbsp;release.<br />Unlike JetpackCompose,&nbsp;<span class=\"hs fz\">SwiftUI is tied to the iOS framework for updates</span>. New SwiftUI APIs will not be backward compatible. However, considering that all devices supporting iOS13 are also supporting iOS14 (unusually, Apple didn&rsquo;t deprecate any device this year), we can safely target iOS14 for apps using SwiftUI.</p>\n" +
                    "<h1 id=\"7a4b\" class=\"jt ju fy bb da jv jw hu jx jy jz hx ka kb kc kd ke kf kg kh ki kj kk kl km kn gv\" data-selectable-paragraph=\"\">Why declarative UIs?</h1>\n" +
                    "<p id=\"54f2\" class=\"hq hr fy hs b ht ko hu hv hw kp hx hy hz kq ia ib ic kr id ie if ks ig ih ij dn gv\" data-selectable-paragraph=\"\"><em class=\"ik\">JetpackCompose</em>&nbsp;and&nbsp;<em class=\"ik\">SwiftUI</em>&nbsp;are both Declarative UI frameworks, which means they are just&nbsp;<span class=\"hs fz\">describing how the UI should look like</span>&nbsp;for the different states,&nbsp;<span class=\"hs fz\">without managing the state directly</span>. The Declarative UI paradigm have become popular thanks to frameworks like&nbsp;<em class=\"ik\">React.js</em>&nbsp;and&nbsp;<em class=\"ik\">Flutter</em>, which have shown how simpler it is to interact with stateless components. Their success has eventually pushed both Android and iOS to join the Declarative UI world!</p>\n" +
                    "<p id=\"658c\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">With&nbsp;<em class=\"ik\">JetpackCompose</em>, you can forget about the cumbersome Android view-based system with the dreadful&nbsp;<em class=\"ik\">Fragments</em>. And with&nbsp;<em class=\"ik\">SwiftUI</em>, you can forget about the&nbsp;<em class=\"ik\">UIKit ViewControllers</em>&nbsp;and the inflexible&nbsp;<em class=\"ik\">Storyboard</em>.&nbsp;<span class=\"hs fz\">It&rsquo;s a clean start. It&rsquo;s the Future!</span></p>\n" +
                    "<div class=\"iy iz ap ja w jb\">\n" +
                    "<div class=\"fg fh lr\">\n" +
                    "<div class=\"jk s ap jl\">\n" +
                    "<div class=\"ls jn s\">\n" +
                    "<div class=\"ep jg ef es eo ex w jh ji jj\"><img class=\"ef es eo ex w jo jp af wm\" src=\"https://miro.medium.com/max/60/1*mpauYGRLOdtXnVMLMGKBvA.png?q=20\" alt=\"\" width=\"700\" height=\"372\" /></div>\n" +
                    "<img class=\"oe tg ef es eo ex w c\" src=\"https://miro.medium.com/max/1400/1*mpauYGRLOdtXnVMLMGKBvA.png\" alt=\"\" width=\"700\" height=\"372\" /></div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<div class=\"iy iz ap ja w jb\">\n" +
                    "<div class=\"fg fh lt\">\n" +
                    "<div class=\"jk s ap jl\">\n" +
                    "<div class=\"lu jn s\">\n" +
                    "<div class=\"ep jg ef es eo ex w jh ji jj\"><img class=\"ef es eo ex w jo jp af wm\" src=\"https://miro.medium.com/max/60/1*TrmNwY-zbSN1gpqgP6cGlw.png?q=20\" alt=\"\" width=\"700\" height=\"374\" /></div>\n" +
                    "<img class=\"oe tg ef es eo ex w c\" src=\"https://miro.medium.com/max/1400/1*TrmNwY-zbSN1gpqgP6cGlw.png\" alt=\"\" width=\"700\" height=\"374\" /></div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<p id=\"27a0\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">The nature of Declarative UIs allows a&nbsp;<span class=\"hs fz\">very neat separation&nbsp;</span>between the&nbsp;<span class=\"hs fz\">UI Layout</span>&nbsp;and the<span class=\"hs fz\">&nbsp;ViewModel</span>, as no extra layer of code (using&nbsp;<em class=\"ik\">findViewById</em>&nbsp;and&nbsp;<em class=\"ik\">@IBOutlet</em>) is needed to connect the two. On this topic, Leland Richardson by&nbsp;<em class=\"ik\">Google</em>&nbsp;wrote an&nbsp;<a class=\"dy iq\" href=\"https://medium.com/androiddevelopers/understanding-jetpack-compose-part-1-of-2-ca316fe39050\" rel=\"noopener\">interesting article</a>, explaining the importance of&nbsp;<span class=\"hs fz\">maximum cohesion</span>&nbsp;and&nbsp;<span class=\"hs fz\">minimum coupling</span>.</p>\n" +
                    "<p id=\"8ed0\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\"><span class=\"hs fz\">JetpackCompose and SwiftUI are very much alike</span>. There are trivial differences about the syntax (JetpackCompose uses&nbsp;<em class=\"ik\">Kotlin</em>, SwiftUI uses&nbsp;<em class=\"ik\">Swift</em>) and the navigation patterns, but the concept behind is exactly the same. And above all, the data to be fed into these two stateless UI frameworks is exactly the same. Because of this, it makes a lot of sense for a&nbsp;<em class=\"ik\">ViewModel</em>&nbsp;to be&nbsp;<em class=\"ik\">platform-independent</em>! We&rsquo;ll talk more in detail about this further on.</p>\n" +
                    "<h1 id=\"35f7\" class=\"jt ju fy bb da jv jw hu jx jy jz hx ka kb kc kd ke kf kg kh ki kj kk kl km kn gv\" data-selectable-paragraph=\"\">DeclarativeUI for Web</h1>\n" +
                    "<p id=\"877f\" class=\"hq hr fy hs b ht ko hu hv hw kp hx hy hz kq ia ib ic kr id ie if ks ig ih ij dn gv\" data-selectable-paragraph=\"\">JetBrains and Google are now working on a Compose version for the Web. Recently a technology preview of&nbsp;<a class=\"dy iq\" href=\"https://blog.jetbrains.com/kotlin/2021/05/technology-preview-jetpack-compose-for-web/\" rel=\"noopener ugc nofollow\" target=\"_blank\"><span class=\"hs fz\">Compose for Web</span></a>&nbsp;has been made publicly available. It is based on the DOM, and it is expected to grow very quickly in the next months.</p>\n" +
                    "<h1 id=\"fbba\" class=\"jt ju fy bb da jv jw hu jx jy jz hx ka kb kc kd ke kf kg kh ki kj kk kl km kn gv\" data-selectable-paragraph=\"\">DeclarativeUI for Desktop</h1>\n" +
                    "<p id=\"f4e7\" class=\"hq hr fy hs b ht ko hu hv hw kp hx hy hz kq ia ib ic kr id ie if ks ig ih ij dn gv\" data-selectable-paragraph=\"\">In the meantime,&nbsp;<em class=\"ik\">JetBrains</em>&nbsp;has already released&nbsp;<a class=\"dy iq\" href=\"https://www.jetbrains.com/lp/compose/\" rel=\"noopener ugc nofollow\" target=\"_blank\"><span class=\"hs fz\">Compose for Desktop</span></a>, which is now in Alpha. It allows the development of desktop apps for&nbsp;<em class=\"ik\">Windows</em>,&nbsp;<em class=\"ik\">macOs</em>&nbsp;and&nbsp;<em class=\"ik\">Linux</em>.</p>\n" +
                    "<p id=\"c0ab\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">In terms of desktop,&nbsp;<span class=\"hs fz\">SwiftUI already supports macOS&nbsp;</span>out of the box. Any UI you write with SwiftUI adapts (with minor changes) to&nbsp;<em class=\"ik\">iOS</em>,&nbsp;<em class=\"ik\">macOS</em>,&nbsp;<em class=\"ik\">tvOS</em>,&nbsp;<em class=\"ik\">watchOS</em>.</p>\n" +
                    "<p id=\"acb4\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">It&rsquo;s easy to envision a near future where&nbsp;<em class=\"ik\">Compose</em>&nbsp;and&nbsp;<em class=\"ik\">SwiftUI</em>&nbsp;will be the only 2 UI frameworks you need to be able to write first-class apps on any platform.</p>\n" +
                    "<div class=\"iy iz ap ja w jb\">\n" +
                    "<div class=\"fg fh lv\">\n" +
                    "<div class=\"jk s ap jl\">\n" +
                    "<div class=\"lw jn s\">\n" +
                    "<div class=\"ep jg ef es eo ex w jh ji jj\"><img class=\"ef es eo ex w jo jp af wm\" src=\"https://miro.medium.com/max/60/1*qM_9WtxGBR18Lqvve63TSA.png?q=20\" alt=\"\" width=\"700\" height=\"383\" /></div>\n" +
                    "<img class=\"oe tg ef es eo ex w c\" src=\"https://miro.medium.com/max/1400/1*qM_9WtxGBR18Lqvve63TSA.png\" alt=\"\" width=\"700\" height=\"383\" /></div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<h1 id=\"f9a9\" class=\"jt ju fy bb da jv jw hu jx jy jz hx ka kb kc kd ke kf kg kh ki kj kk kl km kn gv\" data-selectable-paragraph=\"\">Will Compose eventually support iOS too?</h1>\n" +
                    "<p id=\"5d98\" class=\"hq hr fy hs b ht ko hu hv hw kp hx hy hz kq ia ib ic kr id ie if ks ig ih ij dn gv\" data-selectable-paragraph=\"\">This is a very popular question. I believe it will eventually, mainly thanks to the big community behind Compose. However SwiftUI will easily remain the preferred way to write the UI of an iOS app.</p>\n" +
                    "<p id=\"5de7\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\">But it&rsquo;s a good thing. Having two strong and independent toolkits (Compose and SwiftUI), competing for innovation, is very healthy for the future of UI/UX.</p>\n" +
                    "<p id=\"ad70\" class=\"hq hr fy hs b ht il hu hv hw im hx hy hz in ia ib ic io id ie if ip ig ih ij dn gv\" data-selectable-paragraph=\"\"><em class=\"ik\">Let&rsquo;s now focus more on&nbsp;</em><span class=\"hs fz\"><em class=\"ik\">Kotlin MultiPlatform</em></span><em class=\"ik\">:<br />go to the&nbsp;</em><a class=\"dy iq\" href=\"https://medium.com/@danielebaroncelli/the-future-of-apps-declarative-uis-with-kotlin-multiplatform-d-kmp-part-3-3-1bbadaf19aef\" rel=\"noopener\"><span class=\"hs fz\"><em class=\"ik\">Part 2 of this article</em></span></a><span class=\"hs fz\"><em class=\"ik\">!</em></span></p>\n" +
                    "</div>"


            AndroidView(
                modifier = Modifier,
                factory = { context -> TextView(context) },
                update = {
                    it.text = HtmlCompat.fromHtml(source, HtmlCompat.FROM_HTML_MODE_COMPACT)
                }
            )
            Button(onClick = { navController.navigate(Screens.Help.route) }) {

                Text(text = "click")

            }
        }
    }
}