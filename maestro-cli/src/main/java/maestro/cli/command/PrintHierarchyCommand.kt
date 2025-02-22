/*
 *
 *  Copyright (c) 2022 mobile.dev inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package maestro.cli.command

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import maestro.cli.App
import maestro.cli.DisableAnsiMixin
import maestro.cli.util.MaestroFactory
import maestro.cli.view.green
import picocli.CommandLine

@CommandLine.Command(
    name = "hierarchy",
    description = [
        "Print out the view hierarchy of the connected device"
    ],
    hidden = true
)
class PrintHierarchyCommand : Runnable {

    @CommandLine.Mixin
    var disableANSIMixin: DisableAnsiMixin? = null

    @CommandLine.ParentCommand
    private val parent: App? = null

    override fun run() {
        MaestroFactory.createMaestro(parent?.host, parent?.port, parent?.deviceId)
            .maestro
            .use {
                val hierarchy = jacksonObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(it.viewHierarchy().root)

                println(hierarchy)
            }

        System.err.println("Have you tried running “maestro studio” to visually inspect your app’s UI elements?".green())
    }
}
