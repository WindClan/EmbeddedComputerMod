--- @module security
local expect = dofile("rom/modules/main/cc/expect.lua").expect

local passwordHashFunctions = {
    ["sha256"] = security.hashStrSHA256,
    ["murmur3"] = security.hashStrMurmur3,
    ["adler32"] = security.hashStrAdler32,
    ["siphash24"] = security.hashStrSipHash24,
}

--- Locks the current computer using a hash, can be removed via the peripheral or deleting `.LOCKED_HASHED`
-- @usage Adds a hash lock to the current computer
-- @param The password to lock this computer with
-- @param The hash type to use
-- @since 1.1.0/0.4.0
security.lockHashed = function(password, hashType)
    expect(1,password,"string")
    expect(2,hashType,"string")
    local hashFunction = passwordHashFunctions[hashType:lower()]
    if not hashFunction then
        error("Invalid hash type!",2)
    end
    hashedPass = hashFunction(password)
    for i=0,#password do
        hashedPass = hashFunction(hashedPass)
    end
    for i=0,#password do
        hashedPass = security.hashStrSHA512(hashedPass)
    end
    local a = fs.open("/.LOCKED_HASHED","w")
    a.write(hashedPass)
    a.close()
    return true
end

--- Locks the current computer using an SHA256 hash, can be removed via the peripheral or deleting `.LOCKED_HASHED`
-- @usage Adds an SHA256 lock to the current computer
-- @param The password to lock this computer with
-- @since 1.1.0/0.4.0
security.lock = function(password)
    expect(1,password,"string")
    security.lockHashed(password,"sha256")
end


security.lockInsecure = function(password)
    local a = fs.open("/.LOCKED","w")
    a.write(password)
    a.close()
end