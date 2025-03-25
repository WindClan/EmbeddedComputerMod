if security then
    os.run({},"rom/apis/secure/security.lua")
end
if embedded then
    local function printMessageToMonitor()
        local monitors = {peripheral.find("monitor")}
        for _, monitor in pairs(monitors) do
            local oldColor1 = monitor.getBackgroundColor()
            local oldColor2 = monitor.getTextColor()
            monitor.setTextScale(1)
            monitor.setBackgroundColor(colors.blue)
            monitor.setTextColor(colors.white)
            monitor.setCursorBlink(false)
            monitor.clear()
            monitor.setCursorPos(2,2)
            term.redirect(monitor)
            print(":(")
            print("")
            print(" No startup found")
            print(" Insert a startup disk")
            monitor.setTextColor(oldColor2)
            monitor.setBackgroundColor(oldColor1)
        end
        term.redirect(term.native())
        sleep(10)
    end
    --- Old function to lock computer
    -- @usage Alias from deprecated function to replacement
    -- @param The password to lock this computer with
    -- @changed 1.1.0/0.4.0 It no longer uses the insecure locking, old behaviour can be achieved using `security.lockInsecure`
    embedded.setPassword = security.lock
    if fs.exists("/startup.lua") then
        return
    end
    local found = fs.find("dis*/startup.lua")
    local found1 = fs.find("dis*/startup")
    if #found == 0 and #found1 == 0 then
        printMessageToMonitor()
    else
        return
    end
end