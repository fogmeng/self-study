local key = KEYS[1]
local value = ARGV[1]
local timeout = ARGV[2]
local times = redis.call('INCRBY',key,value)

if times == 1 then
    redis.call('expire',key, timeout)
end
